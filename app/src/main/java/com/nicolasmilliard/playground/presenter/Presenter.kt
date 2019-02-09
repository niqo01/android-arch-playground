package com.nicolasmilliard.playground.presenter

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

interface Presenter<ModelT : Any, EventT : Any> {
    val models: ReceiveChannel<ModelT>
    val events: SendChannel<EventT>
    fun start()
}
