package org.r02_sudhanshu.pokeverse.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatDto(
    @SerialName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: GenericNamedResourceDto
)