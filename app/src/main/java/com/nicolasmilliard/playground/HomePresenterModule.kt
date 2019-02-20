package com.nicolasmilliard.playground

import com.nicolasmilliard.playground.api.ItemService
import com.nicolasmilliard.playground.presenter.HomePresenter
import dagger.Module
import dagger.Provides

@Module
object HomePresenterModule {

    @JvmStatic
    @Provides
    fun provideHomeViewModelFactory(itemService: ItemService): HomePresenter.HomeViewModelFactory {
        return HomePresenter.HomeViewModelFactory(itemService)
    }
}