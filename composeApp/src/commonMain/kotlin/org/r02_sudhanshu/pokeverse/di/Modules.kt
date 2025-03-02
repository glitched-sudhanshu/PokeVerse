package org.r02_sudhanshu.pokeverse.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.r02_sudhanshu.pokeverse.arena.presentation.ArenaViewModel
import org.r02_sudhanshu.pokeverse.core.data.HttpClientFactory
import org.r02_sudhanshu.pokeverse.core.presentation.AudioViewModel
import org.r02_sudhanshu.pokeverse.pokedex.data.database.DatabaseFactory
import org.r02_sudhanshu.pokeverse.pokedex.data.database.FavoritePokemonDatabase
import org.r02_sudhanshu.pokeverse.pokedex.data.network.KtorRemotePokemonDataSource
import org.r02_sudhanshu.pokeverse.pokedex.data.network.RemotePokemonDataSource
import org.r02_sudhanshu.pokeverse.pokedex.data.repository.DefaultPokemonRepository
import org.r02_sudhanshu.pokeverse.pokedex.domain.PokemonRepository
import org.r02_sudhanshu.pokeverse.pokedex.presentation.PokeDexViewModel
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailViewModel
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.PokemonListingViewModel
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