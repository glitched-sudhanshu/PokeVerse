package org.example.pokeverse.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AbilityDto(
    val ability: GenericNamedResourceDto,
    val slot: Int
)