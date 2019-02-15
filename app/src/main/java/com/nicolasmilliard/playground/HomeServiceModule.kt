package com.nicolasmilliard.playground

import com.nicolasmilliard.playground.api.ItemService
import com.nicolasmilliard.playground.api.ItemServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object HomeServiceModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideHomeService(): ItemService {
        return ItemServiceImpl()
    }
}