package com.example.uxcamsample

import android.app.Application
import com.example.uxcamsample.navigation.DemoRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    val KoinNavigationModule = module {

        single<Cicerone<DemoRouter>> {
            Cicerone.create(DemoRouter())
        }

        factory<DemoRouter> {
            val cicerone = get<Cicerone<DemoRouter>>()
            cicerone.router
        }

        factory<NavigatorHolder> {
            val cicerone = get<Cicerone<DemoRouter>>()
            cicerone.getNavigatorHolder()
        }

    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            listOf(
                modules(
                    KoinNavigationModule
                )
            )
        }
    }
}