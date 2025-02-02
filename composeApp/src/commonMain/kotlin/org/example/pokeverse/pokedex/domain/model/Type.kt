package org.example.pokeverse.pokedex.domain.model

data class Type(
    val slot: Int,
    val name: String
) {
    val pokemonType = PokemonType.fromName(name)
}