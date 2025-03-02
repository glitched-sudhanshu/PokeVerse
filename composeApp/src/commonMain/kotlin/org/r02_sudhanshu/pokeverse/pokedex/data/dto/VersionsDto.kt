package org.r02_sudhanshu.pokeverse.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionsDto(
    @SerialName("generation-v") val generationV: GenerationVDto
)