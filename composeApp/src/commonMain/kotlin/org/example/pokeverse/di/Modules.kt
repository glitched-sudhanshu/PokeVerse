package org.example.pokeverse.di

import org.example.pokeverse.core.data.HttpClientFactory
import org.example.pokeverse.pokedex.data.network.KtorRemotePokemonDataSource
import org.example.pokeverse.pokedex.data.network.RemotePokemonDataSource
import org.example.pokeverse.pokedex.data.repository.DefaultPokemonRepository
import org.example.pokeverse.pokedex.domain.PokemonRepository
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokeDexViewModel
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
    viewModelOf(::PokeDexViewModel)
}