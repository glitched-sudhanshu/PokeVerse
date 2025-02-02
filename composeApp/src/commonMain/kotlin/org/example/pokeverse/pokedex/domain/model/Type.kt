package org.example.pokeverse.pokedex.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val slot: Int,
    val name: String
) {
    val pokemonType = PokemonType.fromName(name)
}