package com.nicolasmilliard.playground.ui

import android.os.Bundle
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import com.nicolasmilliard.playground.R

@ContentView(R.layout.screen_detail)
class DetailFragment : Fragment() {

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
            param1 = args.notificationId
        }
    }
}
