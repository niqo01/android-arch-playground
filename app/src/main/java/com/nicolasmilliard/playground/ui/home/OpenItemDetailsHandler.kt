package com.nicolasmilliard.playground.ui.home

import androidx.navigation.NavController
import com.nicolasmilliard.playground.service.Item

class OpenItemDetailsHandler(
    private val navController: NavController
) : ItemHandler {
    override fun invoke(item: Item) {
        val action =
            HomeScreenDirections.actionHomeScreenToItemDetailScreen(
                "Test"
            )
        navController.navigate(action)
    }
}