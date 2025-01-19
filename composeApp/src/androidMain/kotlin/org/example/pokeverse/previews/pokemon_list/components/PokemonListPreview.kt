package org.example.pokeverse.previews.pokemon_list.components

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokeverse.pokedex.presentation.pokemon_list.components.PokemonList
import org.example.pokeverse.previews.pokemon_list.pokemonList

@Preview
@Composable
private fun PokemonListPreview() {
    PokemonList(
        pokemons = pokemonList,
        modifier = Modifier,
        listState = rememberLazyGridState(),
        onClick = {},
        onLikeClick = {}
    )
}