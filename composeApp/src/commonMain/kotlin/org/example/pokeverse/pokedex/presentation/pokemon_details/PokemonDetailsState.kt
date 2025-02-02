package org.example.pokeverse.pokedex.presentation.pokemon_details

import org.example.pokeverse.pokedex.domain.model.Ability
import org.example.pokeverse.pokedex.domain.model.Animated
import org.example.pokeverse.pokedex.domain.model.BlackWhite
import org.example.pokeverse.pokedex.domain.model.Cries
import org.example.pokeverse.pokedex.domain.model.GenerationV
import org.example.pokeverse.pokedex.domain.model.GenericNamedResource
import org.example.pokeverse.pokedex.domain.model.Move
import org.example.pokeverse.pokedex.domain.model.Pokemon
import org.example.pokeverse.pokedex.domain.model.Sprites
import org.example.pokeverse.pokedex.domain.model.Stat
import org.example.pokeverse.pokedex.domain.model.Type
import org.example.pokeverse.pokedex.domain.model.Versions

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