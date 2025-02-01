package org.example.pokeverse.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoveDto(
    val move: GenericNamedResourceDto,
)