package com.nicolasmilliard.playground.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import butterknife.BindView
import butterknife.ButterKnife.bind
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicolasmilliard.playground.R
import dagger.Module
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.bottom_nav)
    lateinit var bottomNav: BottomNavigationView

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        bind(this)

        val navController = findNavController(R.id.nav_host_fragment)
        bottomNav.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment -> showBottomNav(false)
                else -> showBottomNav(true)
            }
        }
    }

    private fun showBottomNav(show: Boolean) {
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
