package com.nicolasmilliard.playground

import android.app.Application
import com.nicolasmilliard.playground.ui.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HomeServiceModule::class,
        HomePresenterModule::class,
        MainActivityModule::class,
        AndroidInjectionModule::class
    ]
)
interface ReleaseAppComponent : AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ReleaseAppComponent
    }
}

fun Application.createAppComponent() = DaggerReleaseAppComponent.builder()
    .application(this)
    .build()