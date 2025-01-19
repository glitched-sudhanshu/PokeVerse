package org.example.pokeverse.previews.pokemon_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreen
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListState

@Preview
@Composable
private fun PokemonListScreenPreview(modifier: Modifier = Modifier) {
    PokemonListScreen(
        state = PokemonListState(),
        onAction = {}
    )
}