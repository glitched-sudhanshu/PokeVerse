package org.example.pokeverse.pokedex.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    val baseStat: Int,
    val effort: Int,
    val stat: String
)