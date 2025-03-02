package org.r02_sudhanshu.pokeverse.pokedex.data.database.entities

import kotlinx.serialization.Serializable

@Serializable
data class MoveEntity(
    val move: String,
)