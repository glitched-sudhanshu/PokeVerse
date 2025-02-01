package org.example.pokeverse.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val abilities: List<AbilityDto>,
    @SerialName("base_experience") val baseExperience: Int,
    val cries: CriesDto,
    val height: Int,
    val id: Int,
    val moves: List<MoveDto>,
    val name: String,
    val species: GenericNamedResourceDto,
    val sprites: SpritesDto,
    val stats: List<StatDto>,
    val types: List<TypeDto>,
    val weight: Int
)