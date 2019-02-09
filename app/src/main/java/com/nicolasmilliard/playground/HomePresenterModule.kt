package com.nicolasmilliard.playground

import com.nicolasmilliard.playground.presenter.HomeViewModel
import com.nicolasmilliard.playground.service.HomeService
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