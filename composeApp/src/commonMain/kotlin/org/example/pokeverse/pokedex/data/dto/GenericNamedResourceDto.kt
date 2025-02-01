package org.example.pokeverse.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenericNamedResourceDto(
    val name: String,
    val url: String
)