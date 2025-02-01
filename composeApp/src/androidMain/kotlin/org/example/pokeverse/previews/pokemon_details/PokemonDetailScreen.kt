package org.example.pokeverse.previews.pokemon_details

import androidx.compose.runtime.Composable
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreen
import org.example.pokeverse.pokedex.presentation.pokemon_details.testPokemon


@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun PokemonDetailsScreenPreview() {
    PokemonDetailScreen(pokemon = testPokemon)
}