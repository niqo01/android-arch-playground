package com.nicolasmilliard.playground.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nicolasmilliard.playground.R

class ItemDetailScreen : Fragment(R.layout.screen_item_detail) {

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = ItemDetailScreenArgs.fromBundle(it)
            param1 = args.notificationId
        }
    }
}
