package org.example.pokeverse.app

import kotlinx.serialization.Serializable

sealed class PokemonRoute {
    @Serializable
    data object PokemonNavGraph : PokemonRoute()

    @Serializable
    data object PokemonListing : PokemonRoute()

    @Serializable
    data class PokemonDetails(val id: Int) : PokemonRoute()
}