package org.example.pokeverse.pokedex.domain.model

import kotlinx.serialization.SerialName

data class Stat(
    @SerialName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: GenericNamedResource
)