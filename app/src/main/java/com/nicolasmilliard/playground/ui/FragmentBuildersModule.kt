package com.nicolasmilliard.playground.ui

import com.nicolasmilliard.playground.ScreenScope
import com.nicolasmilliard.playground.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ScreenScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment
}
