package com.nicolasmilliard.playground

import com.nicolasmilliard.playground.presenter.HomePresenter
import com.nicolasmilliard.playground.service.HomeService
import dagger.Module
import dagger.Provides

@Module
object HomePresenterModule {

    @JvmStatic
    @Provides
    fun provideHomeViewModelFactory(homeService: HomeService): HomePresenter.HomeViewModelFactory {
        return HomePresenter.HomeViewModelFactory(homeService)
    }
}