package org.example.pokeverse

import android.app.Application
import org.example.pokeverse.di.initKoin
import org.koin.android.ext.koin.androidContext

class PokeVerseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityHolder)
        initKoin {
            androidContext(this@PokeVerseApplication)
        }
    }
}