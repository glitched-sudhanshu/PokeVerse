package org.example.pokeverse.pokedex.presentation.pokemon_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.pokeverse.core.presentation.DarkBlue
import org.example.pokeverse.core.presentation.DesertWhite
import org.example.pokeverse.core.presentation.SandYellow
import org.example.pokeverse.pokedex.domain.model.Pokemon
import org.example.pokeverse.pokedex.presentation.pokemon_list.components.PokeVerseSearchBar
import org.example.pokeverse.pokedex.presentation.pokemon_list.components.PokemonList
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.favorites
import pokeverse.composeapp.generated.resources.no_favorite_pokemons
import pokeverse.composeapp.generated.resources.no_pokemons_listing
import pokeverse.composeapp.generated.resources.no_search_results
import pokeverse.composeapp.generated.resources.pokeball_loading
import pokeverse.composeapp.generated.resources.search_results

@Composable
fun PokemonListScreenRoot(
    pokeDexViewModel: PokeDexViewModel = koinViewModel(),
    onPokemonClick: (Pokemon) -> Unit,
) {
    val state by pokeDexViewModel.state.collectAsStateWithLifecycle()

    PokemonListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is PokemonListAction.OnPokemonClick -> {
                    onPokemonClick(action.pokemon)
                }

                else -> {
                    pokeDexViewModel.onAction(action)
                }
            }
        },
        loadNextPagePokemons = {
            pokeDexViewModel.getPokemonListing()
        }
    )

}

@Composable
fun PokemonListScreen(
    state: PokemonListState,
    onAction: (PokemonListAction) -> Unit,
    loadNextPagePokemons: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        val pagerState = rememberPagerState { 2 }
        val pokemonsListState = rememberLazyGridState()
        val favoritePokemonsListState = rememberLazyGridState()

        LaunchedEffect(state.searchResult) {
            pokemonsListState.animateScrollToItem(0)
        }

        LaunchedEffect(state.selectedTabIndex) {
            pagerState.animateScrollToPage(state.selectedTabIndex)
        }

        LaunchedEffect(pagerState.currentPage) {
            onAction(PokemonListAction.OnTabSelected(pagerState.currentPage))
        }


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
        Surface(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = DesertWhite
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    containerColor = DesertWhite,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[state.selectedTabIndex])
                        )
                    }
                ) {
                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onAction(PokemonListAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                    Tab(
                        selected = state.selectedTabIndex == 1,
                        onClick = {
                            onAction(PokemonListAction.OnTabSelected(1))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favorites),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (pageIndex) {
                            0 -> {
                                if (state.isLoading) {
                                    Image(
                                        painter = painterResource(Res.drawable.pokeball_loading),
                                        contentDescription = "page-loader",
                                        modifier = Modifier.size(64.dp)
                                    )
                                } else {
                                    when {
                                        state.errorMessage != null -> {
                                            Text(
                                                text = state.errorMessage.asString(),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        else -> {
                                            PokemonList(
                                                pokemons = if (state.searchQuery.isNullOrBlank()) {
                                                    state.pokemonsListing
                                                } else {
                                                    state.searchResult
                                                },
                                                errorMessage = if (state.searchQuery.isNullOrBlank()) stringResource(
                                                    Res.string.no_pokemons_listing
                                                ) else stringResource(Res.string.no_search_results),
                                                onClick = {
                                                    onAction(PokemonListAction.OnPokemonClick(it))
                                                },
                                                modifier = Modifier.fillMaxSize(),
                                                listState = pokemonsListState,
                                                isNextPageLoading = state.isNextPageLoading,
                                                loadNextPagePokemons = {
                                                    if (state.searchQuery.isNullOrBlank() && !state.isLoading && !state.isNextPageLoading) {
                                                        loadNextPagePokemons()
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                PokemonList(
                                    pokemons = state.favouritePokemons,
                                    onClick = {
                                        onAction(PokemonListAction.OnPokemonClick(it))
                                    },
                                    modifier = Modifier.fillMaxSize(),
                                    listState = favoritePokemonsListState,
                                    errorMessage = stringResource(Res.string.no_favorite_pokemons),
                                    isNextPageLoading = state.isNextPageLoading,
                                    loadNextPagePokemons = {}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}