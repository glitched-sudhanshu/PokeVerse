package org.example.pokeverse.pokedex.domain.model

data class Type(
    val slot: Int,
    val type: GenericNamedResource
) {
    val pokemonType = PokemonType.fromName(type.name)
}