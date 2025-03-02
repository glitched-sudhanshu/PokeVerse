package org.r02_sudhanshu.pokeverse.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CriesDto(
    val latest: String?,
    val legacy: String?
)