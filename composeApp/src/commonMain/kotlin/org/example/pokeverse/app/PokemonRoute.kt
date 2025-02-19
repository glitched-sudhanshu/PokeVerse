package org.example.pokeverse.app

import kotlinx.serialization.Serializable

sealed class PokemonMainRoute {
    @Serializable
    data object PokemonListingNavGraph : PokemonMainRoute()

    @Serializable
    data object PokemonArenaNavGraph : PokemonMainRoute()
}

sealed class PokedexRoute : PokemonMainRoute() {
    @Serializable
    data object PokemonListing : PokedexRoute()

    @Serializable
    data class PokemonDetails(val id: Int) : PokedexRoute()
}


sealed class ArenaRoute : PokemonMainRoute() {

}