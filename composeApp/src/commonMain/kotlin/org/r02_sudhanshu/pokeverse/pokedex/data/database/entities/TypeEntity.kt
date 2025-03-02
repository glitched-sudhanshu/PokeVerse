package org.r02_sudhanshu.pokeverse.pokedex.data.database.entities

import kotlinx.serialization.Serializable

@Serializable
data class TypeEntity(
    val slot: Int,
    val name: String
)