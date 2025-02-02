package org.example.pokeverse.pokedex.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    val ability: String,
    val slot: Int
)