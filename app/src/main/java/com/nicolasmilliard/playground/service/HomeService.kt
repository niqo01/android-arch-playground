package com.nicolasmilliard.playground.service

interface HomeService {
    suspend fun loadData(): List<Item>
}