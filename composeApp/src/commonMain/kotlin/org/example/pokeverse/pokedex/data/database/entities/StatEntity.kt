package org.example.pokeverse.pokedex.data.database.entities

import kotlinx.serialization.Serializable

@Serializable
data class StatEntity(
    val baseStat: Int,
    val effort: Int,
    val stat: String
)