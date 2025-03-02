package org.r02_sudhanshu.pokeverse.previews.pokemon_details

import androidx.compose.runtime.Composable
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreen
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details.testPokemon


@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun PokemonDetailsScreenPreview() {
    PokemonDetailScreen(pokemon = testPokemon, false, onAction = {})
}