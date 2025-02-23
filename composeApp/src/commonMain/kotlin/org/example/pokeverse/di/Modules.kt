package org.example.pokeverse.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.pokeverse.arena.presentation.ArenaViewModel
import org.example.pokeverse.core.data.AudioRepositoryImpl
import org.example.pokeverse.core.data.HttpClientFactory
import org.example.pokeverse.core.domain.AudioRepository
import org.example.pokeverse.core.presentation.AudioViewModel
import org.example.pokeverse.pokedex.data.database.DatabaseFactory
import org.example.pokeverse.pokedex.data.database.FavoritePokemonDatabase
import org.example.pokeverse.pokedex.data.network.KtorRemotePokemonDataSource
import org.example.pokeverse.pokedex.data.network.RemotePokemonDataSource
import org.example.pokeverse.pokedex.data.repository.DefaultPokemonRepository
import org.example.pokeverse.pokedex.domain.PokemonRepository
import org.example.pokeverse.pokedex.presentation.PokeDexViewModel
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailViewModel
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListingViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemotePokemonDataSource).bind<RemotePokemonDataSource>()
    singleOf(::DefaultPokemonRepository).bind<PokemonRepository>()

    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single { get<FavoritePokemonDatabase>().favoritePokemonDao }

    viewModelOf(::PokeDexViewModel)
    viewModelOf(::PokemonListingViewModel)
    viewModelOf(::PokemonDetailViewModel)
    viewModelOf(::ArenaViewModel)
    viewModelOf(::AudioViewModel)
}