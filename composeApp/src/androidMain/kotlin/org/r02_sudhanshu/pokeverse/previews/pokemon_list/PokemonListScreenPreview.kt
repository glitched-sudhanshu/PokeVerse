package org.r02_sudhanshu.pokeverse.previews.pokemon_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreen
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.PokemonListState

@Preview
@Composable
private fun PokemonListScreenPreview(modifier: Modifier = Modifier) {
    PokemonListScreen(
        state = PokemonListState(),
        onAction = {},
        loadNextPagePokemons = {}
    )
}