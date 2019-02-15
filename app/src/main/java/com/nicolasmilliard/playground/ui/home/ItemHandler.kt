package com.nicolasmilliard.playground.ui.home

import com.nicolasmilliard.playground.api.Item

interface ItemHandler {
    operator fun invoke(item: Item)
}