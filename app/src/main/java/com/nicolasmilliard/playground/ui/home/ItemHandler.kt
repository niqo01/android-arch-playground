package com.nicolasmilliard.playground.ui.home

import com.nicolasmilliard.playground.service.Item

interface ItemHandler {
    operator fun invoke(item: Item)
}