package org.example.pokeverse.pokedex.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Move(
    val move: String,
)