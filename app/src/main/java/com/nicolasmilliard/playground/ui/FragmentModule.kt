package com.nicolasmilliard.playground.ui

import com.nicolasmilliard.playground.Root
import com.nicolasmilliard.playground.Screen
import com.nicolasmilliard.playground.ScreenScope
import com.squareup.picasso3.Picasso
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module
class FragmentModule {

    @Provides
    @ScreenScope
    @Screen
    fun providePicasso(@Root picasso: Picasso): Picasso {
        return picasso.newBuilder().build()
    }
}
