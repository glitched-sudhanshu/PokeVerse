package org.r02_sudhanshu.pokeverse.previews.pokemon_list.components

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.components.PokemonList
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.pokemonList
import org.jetbrains.compose.resources.stringResource
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.no_search_results

@Preview
@Composable
private fun PokemonListPreview() {
    PokemonList(
        pokemons = pokemonList,
        modifier = Modifier,
        listState = rememberLazyGridState(),
        onClick = {},
        loadNextPagePokemons = {},
        errorMessage = stringResource(resource = Res.string.no_search_results)
    )
}