package com.nicolasmilliard.playground.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nicolasmilliard.playground.service.HomeService
import com.nicolasmilliard.playground.service.Item
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import timber.log.Timber

class HomePresenter(private val homeService: HomeService) : ViewModel(), Presenter<Model, Event> {

    private val _models = ConflatedBroadcastChannel<Model>()
    override val models: ReceiveChannel<Model> get() = _models.openSubscription()

    private val _events = Channel<Event>(Channel.RENDEZVOUS)
    override val events: SendChannel<Event> get() = _events

    init {
        start()
    }

    override fun start() {
        viewModelScope.launch {
            var model = Model()
            fun sendModel(newModel: Model) {
                model = newModel
                _models.offer(newModel)
            }
            sendModel(model)
            launch {
                val data = homeService.loadData()
                sendModel(model.copy(false, data))
            }
            Timber.i("")
        }
    }

    class HomeViewModelFactory constructor(private val homeService: HomeService) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomePresenter::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomePresenter(homeService) as T
            }
            throw IllegalStateException("Impossibles")
        }
    }
}

sealed class Event {
    object OnAttachEvent
}

data class Model(val isLoading: Boolean = true, val data: List<Item> = emptyList())
