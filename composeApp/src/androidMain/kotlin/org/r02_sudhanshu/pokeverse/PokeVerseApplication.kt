package org.r02_sudhanshu.pokeverse

import android.app.Application
import org.r02_sudhanshu.pokeverse.di.initKoin
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