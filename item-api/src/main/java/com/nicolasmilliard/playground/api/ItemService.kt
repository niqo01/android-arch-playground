package com.nicolasmilliard.playground.api

interface ItemService {
    suspend fun load(): List<Item>
}