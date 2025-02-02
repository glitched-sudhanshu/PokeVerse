package org.example.pokeverse.pokedex.domain.model

data class Pokemon(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val cries: Cries,
    val height: Int,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val species: GenericNamedResource,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {
    val imageUrl = sprites.frontDefault ?: sprites.frontShiny ?: sprites.frontFemale
    ?: sprites.frontShinyFemale
}