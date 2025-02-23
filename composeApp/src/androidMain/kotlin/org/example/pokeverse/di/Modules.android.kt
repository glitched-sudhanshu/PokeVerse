package org.example.pokeverse.di

import android.app.Activity
import androidx.media3.exoplayer.ExoPlayer
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.example.pokeverse.ActivityHolder
import org.example.pokeverse.core.data.AudioRepositoryImpl
import org.example.pokeverse.core.domain.AudioRepository
import org.example.pokeverse.pokedex.data.database.DatabaseFactory
import org.example.pokeverse.pokedex.domain.PokemonRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single { DatabaseFactory(androidApplication()) }
        single<Activity?> { ActivityHolder.getActivity() }
        single<ExoPlayer> { ExoPlayer.Builder(androidApplication()).build() }
        single<AudioRepository> { AudioRepositoryImpl(get()) }
    }