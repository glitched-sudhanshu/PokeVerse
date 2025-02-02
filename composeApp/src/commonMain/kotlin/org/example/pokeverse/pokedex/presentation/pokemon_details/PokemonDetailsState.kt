package org.example.pokeverse.pokedex.presentation.pokemon_details

import org.example.pokeverse.pokedex.domain.model.Ability
import org.example.pokeverse.pokedex.domain.model.Move
import org.example.pokeverse.pokedex.domain.model.Pokemon
import org.example.pokeverse.pokedex.domain.model.Stat
import org.example.pokeverse.pokedex.domain.model.Type

data class PokemonDetailsState(
    val pokemon: Pokemon?,
    val isFavourite: Boolean = false,
)


val testPokemon = Pokemon(
    types = listOf(
        Type(
            1,
            "fairy"
        )
    ),
    name = "clefairy",
    id = 4,
    height = 12,
    moves = listOf(Move("Pound")),
    weight = 13,
    abilities = listOf(
        Ability("friend-guard", 1)
    ),
    soundUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
    baseExperience = 124,
    species = "dragon",
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
    gifUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
    stats = listOf(
        Stat(34, 45, "Killos")
    )
)