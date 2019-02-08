package com.nicolasmilliard.playground.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import butterknife.ButterKnife.bind
import butterknife.OnClick
import butterknife.Unbinder
import com.nicolasmilliard.playground.R

@ContentView(R.layout.screen_notifications)
class NotificationsFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var unbinder: Unbinder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = bind(this, view)
        navController = view.findNavController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    @OnClick(R.id.button)
    internal fun onButton() {
        val action =
            NotificationsFragmentDirections.actionNotificationsFragmentToDetailFragment(
                "Test"
            )
        navController.navigate(action)
    }

    @OnClick(R.id.setting_button)
    internal fun onSettingsClick() {
        val action =
            NotificationsFragmentDirections.actionNotificationsFragmentToSettingsFragment()
        navController.navigate(action)
    }
}
