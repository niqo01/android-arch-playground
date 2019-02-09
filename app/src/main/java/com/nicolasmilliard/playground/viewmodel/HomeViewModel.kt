package com.nicolasmilliard.playground.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jakewharton.rxrelay2.BehaviorRelay
import com.nicolasmilliard.playground.ui.HomeService
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class HomeViewModel(val homeService: HomeService) : ViewModel() {

    private val model = BehaviorRelay.createDefault<UiModel>(UiModel(true, ""))

    fun observe() = model

    fun loadData() = viewModelScope.launch {
        //        model.accept(UiModel(true, ""))
        val data = homeService.loadData()
        model.accept(UiModel(false, data))
    }

    class HomeViewModelFactory constructor(private val homeService: HomeService) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(homeService) as T
            }
            throw IllegalStateException("")
        }
    }
}

sealed class Events {
    object OnAttachEvent
}

data class UiModel(val isLoading: Boolean, val data: String)
