package org.example.pokeverse.pokedex.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Stat(
    @SerialName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: GenericNamedResource
)