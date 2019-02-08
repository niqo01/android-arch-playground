package com.nicolasmilliard.playground.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeService {
    suspend fun loadData() = withContext(Dispatchers.IO) {
        delay(10000)
        "Text Data"
    }
}