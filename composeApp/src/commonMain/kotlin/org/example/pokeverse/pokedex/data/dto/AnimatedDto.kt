package org.example.pokeverse.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimatedDto(
    @SerialName("back-default") val backDefault: String,
    @SerialName("back-female") val backFemale: String?,
    @SerialName("back-shiny") val backShiny: String,
    @SerialName("back-shiny-female") val backShinyFemale: String?,
    @SerialName("front-default") val frontDefault: String,
    @SerialName("front-female") val frontFemale: String?,
    @SerialName("front-shiny") val frontShiny: String,
    @SerialName("front-shiny-female") val frontShinyFemale: String?
)
