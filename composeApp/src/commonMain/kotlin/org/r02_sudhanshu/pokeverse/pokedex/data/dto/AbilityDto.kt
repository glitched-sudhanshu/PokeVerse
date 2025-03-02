package org.r02_sudhanshu.pokeverse.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AbilityDto(
    val ability: GenericNamedResourceDto,
    val slot: Int
)