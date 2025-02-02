package org.example.pokeverse.pokedex.domain.model

data class Pokemon(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val soundUrl: String?,
    val height: Int,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val species: String,
    val imageUrl: String?,
    val gifUrl: String?,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)