package org.example.pokeverse.previews.pokemon_details

import androidx.compose.runtime.Composable
import org.example.pokeverse.pokedex.domain.Ability
import org.example.pokeverse.pokedex.domain.Move
import org.example.pokeverse.pokedex.domain.Pokemon
import org.example.pokeverse.pokedex.domain.PokemonType
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreen


@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun PokemonDetailsScreenPreview() {
    PokemonDetailScreen(
        pokemon = Pokemon(
            types = listOf(PokemonType.Fairy),
            name = "clefairy",
            id = "$23",
            height = 12,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
            moves = listOf(Move("pound")),
            weight = 13,
            abilities = listOf(Ability("friend-guard")),
            baseExperience = 124,
        )
    )
}