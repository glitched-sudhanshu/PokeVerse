package org.example.pokeverse.pokedex.presentation.pokemon_details

import org.example.pokeverse.pokedex.domain.Ability
import org.example.pokeverse.pokedex.domain.Animated
import org.example.pokeverse.pokedex.domain.BlackWhite
import org.example.pokeverse.pokedex.domain.Cries
import org.example.pokeverse.pokedex.domain.GenerationV
import org.example.pokeverse.pokedex.domain.GenericNamedResource
import org.example.pokeverse.pokedex.domain.Move
import org.example.pokeverse.pokedex.domain.Pokemon
import org.example.pokeverse.pokedex.domain.Sprites
import org.example.pokeverse.pokedex.domain.Stat
import org.example.pokeverse.pokedex.domain.Type
import org.example.pokeverse.pokedex.domain.Versions

data class PokemonDetailsState(
    val pokemon: Pokemon?
)


val testPokemon = Pokemon(
    types = listOf(
        Type(
            1,
            GenericNamedResource(
                "fairy",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
            )
        )
    ),
    name = "clefairy",
    id = 4,
    height = 12,
    moves = listOf(
        Move(
            GenericNamedResource(
                "Pound",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
            )
        )
    ),
    weight = 13,
    abilities = listOf(
        Ability(
            GenericNamedResource(
                "friend-guard",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
            ),
            1
        )
    ),
    cries = Cries(
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
    ),
    baseExperience = 124,
    species = GenericNamedResource(
        "dragon",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
    ),
    sprites = Sprites(
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        versions = Versions(
            GenerationV(
                BlackWhite(
                    Animated(
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                    )
                )
            )
        ),
    ),
    stats = listOf(
        Stat(
            34, 45, GenericNamedResource(
                "Killos",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
            )
        )
    )
)