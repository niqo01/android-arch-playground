package com.nicolasmilliard.playground.ui.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class HomeFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}
