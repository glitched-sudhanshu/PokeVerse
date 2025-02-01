package org.example.pokeverse.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TypeDto(
    val slot: Int,
    val type: GenericNamedResourceDto
)