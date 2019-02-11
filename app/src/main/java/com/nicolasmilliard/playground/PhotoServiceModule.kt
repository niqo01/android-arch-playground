package com.nicolasmilliard.playground

import android.app.Application
import android.graphics.Bitmap
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import com.squareup.picasso3.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PhotoServiceModule {

    @JvmStatic
    @Provides
    @Singleton
    @Root
    fun providePicasso(application: Application): Picasso {
        return Picasso.Builder(application)
            .defaultBitmapConfig(if (SDK_INT >= O) Bitmap.Config.HARDWARE else Bitmap.Config.RGB_565)
            .build()
    }
}