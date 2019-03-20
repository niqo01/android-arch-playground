package com.nicolasmilliard.playground.ui.home

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.Screen
import com.nicolasmilliard.playground.presenter.HomePresenter
import com.nicolasmilliard.playground.ui.Injectable
import com.nicolasmilliard.playground.ui.bindTo
import com.squareup.picasso3.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ContentView(R.layout.screen_home)
class HomeScreen : Fragment(), Injectable {

    private val binderJob = Job()
    private val scope = CoroutineScope(Dispatchers.Main + binderJob)

    @Inject
    @field:Screen
    lateinit var picasso: Picasso
    @Inject
    lateinit var homePresenterFactory: HomePresenter.HomeViewModelFactory
    private lateinit var homePresenter: HomePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homePresenter = ViewModelProviders.of(this, homePresenterFactory).get()
        lifecycle.addObserver(picasso)

        val onClick = OpenItemDetailsHandler(findNavController())

        scope.launch(Main.immediate) {
            val binder = HomeUiBinder(view, picasso, onClick)
            binder.bindTo(homePresenter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binderJob.cancel()
    }
}
