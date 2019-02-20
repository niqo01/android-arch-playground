package com.nicolasmilliard.playground.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nicolasmilliard.playground.api.Item
import com.nicolasmilliard.playground.api.ItemService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch

class HomePresenter(private val itemService: ItemService) : ViewModel(), Presenter<Model, Event> {

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
                val data = itemService.load()
                sendModel(model.copy(false, data))
            }
        }
    }

    class HomeViewModelFactory constructor(private val itemService: ItemService) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomePresenter::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomePresenter(itemService) as T
            }
            throw IllegalStateException("Impossibles")
        }
    }
}

sealed class Event {
    object OnAttachEvent
}

data class Model(val isLoading: Boolean = true, val data: List<Item> = emptyList())
