package com.nicolasmilliard.playground.ui.home

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.presenter.HomeViewModel
import com.nicolasmilliard.playground.ui.Injectable
import com.nicolasmilliard.playground.ui.bindTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ContentView(R.layout.screen_home)
class HomeFragment : Fragment(), Injectable {

    private val binderJob = Job()
    private val scope = CoroutineScope(Dispatchers.Main + binderJob)

    @Inject
    lateinit var homeViewModelFactory: HomeViewModel.HomeViewModelFactory
    private lateinit var homeViewModel: HomeViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, homeViewModelFactory).get()

        scope.launch(Dispatchers.Unconfined) {
            val binder = HomeUiBinder(view)
            binder.bindTo(homeViewModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binderJob.cancel()
    }


}
