package org.example.pokeverse.pokedex.presentation.pokemon_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.pokeverse.core.presentation.DarkBlue
import org.example.pokeverse.pokedex.domain.Pokemon
import org.example.pokeverse.pokedex.presentation.pokemon_list.components.PokeVerseSearchBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PokemonListScreenRoot(
    pokemonListViewModel: PokemonListViewModel = koinViewModel(),
    onPokemonClick: (Pokemon) -> Unit,
) {

    val state by pokemonListViewModel.state.collectAsStateWithLifecycle()

    PokemonListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is PokemonListAction.OnPokemonClick -> {
                    onPokemonClick(action.pokemon)
                }

                else -> Unit
            }
            pokemonListViewModel.onAction(action)
        }
    )

}

@Composable
private fun PokemonListScreen(
    state: PokemonListState,
    onAction: (PokemonListAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        PokeVerseSearchBar(
            modifier = Modifier.widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp),
            onImeSearch = {
                keyboardController?.hide()
            },
            onSearchQueryChange = {
                onAction(PokemonListAction.OnSearchQueryChange(it))
            },
            searchQuery = state.searchQuery ?: ""
        )
    }
}