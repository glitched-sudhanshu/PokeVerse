package org.r02_sudhanshu.pokeverse.di

import android.app.Activity
import androidx.media3.exoplayer.ExoPlayer
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.r02_sudhanshu.pokeverse.ActivityHolder
import org.r02_sudhanshu.pokeverse.core.data.AudioRepositoryImpl
import org.r02_sudhanshu.pokeverse.core.domain.AudioRepository
import org.r02_sudhanshu.pokeverse.pokedex.data.database.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single { DatabaseFactory(androidApplication()) }
        single<Activity?> { ActivityHolder.getActivity() }
        single<ExoPlayer> { ExoPlayer.Builder(androidApplication()).build() }
        single<AudioRepository> { AudioRepositoryImpl(get()) }
    }