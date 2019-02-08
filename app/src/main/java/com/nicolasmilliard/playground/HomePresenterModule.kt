package com.nicolasmilliard.playground

import com.nicolasmilliard.playground.ui.HomeService
import com.nicolasmilliard.playground.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
object HomePresenterModule {

    @JvmStatic
    @Provides
    fun provideHomeViewModelFactory(homeService: HomeService): HomeViewModel.HomeViewModelFactory {
        return HomeViewModel.HomeViewModelFactory(homeService)
    }
}