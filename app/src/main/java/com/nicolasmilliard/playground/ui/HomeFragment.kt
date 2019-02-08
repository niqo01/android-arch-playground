package com.nicolasmilliard.playground.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.viewmodel.HomeViewModel
import io.reactivex.disposables.Disposables
import javax.inject.Inject

@ContentView(R.layout.screen_loading)
class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModel.HomeViewModelFactory
    lateinit var homeViewModel: HomeViewModel

    private var disposable = Disposables.empty()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, homeViewModelFactory).get()

        val constraintLayout = (view as ConstraintLayout)
            .apply { loadLayoutDescription(R.xml.home_states) }

        disposable = homeViewModel.observe().subscribe {
            constraintLayout.apply {
                when {
                    it.isLoading -> setState(R.id.loading, 0, 0)
                    !it.isLoading -> setState(R.id.home, 0, 0)
                }
            }
        }
        homeViewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }
}
