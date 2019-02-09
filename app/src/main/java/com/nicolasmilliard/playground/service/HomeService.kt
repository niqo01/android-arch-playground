package com.nicolasmilliard.playground.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeService {
    suspend fun loadData() = withContext(Dispatchers.IO) {
        delay(3000)
        listOf<Item>(
            Item("id1", "Title 1", "Description 1"),
            Item("id2", "Title 2", "Description 2"),
            Item("id3", "Title 3", "Description 3"),
            Item("id4", "Title 4", "Description 4")
        )
    }
}