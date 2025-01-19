package org.example.pokeverse.pokedex.domain

data class Pokemon(
    val id: String,
    val name: String,
    val imageUrl: String,
    val weight: Int,
    val height: Int,
    val baseExperience: Int,
    val abilities: List<Ability>,
    val moves: List<Move>
)