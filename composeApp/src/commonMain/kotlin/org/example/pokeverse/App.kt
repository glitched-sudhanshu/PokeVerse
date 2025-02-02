package org.example.pokeverse

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.client.engine.HttpClientEngine
import org.example.pokeverse.core.data.HttpClientFactory
import org.example.pokeverse.pokedex.data.network.KtorRemotePokemonDataSource
import org.example.pokeverse.pokedex.data.repository.DefaultPokemonRepository
import org.example.pokeverse.pokedex.presentation.PokemonRoute
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreen
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListAction
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreenRoot
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = PokemonRoute.PokemonListing
        ) {
            composable<PokemonRoute.PokemonListing> { backStackEntry ->
//                val viewmodel: PokemonListViewModel = viewModel(backStackEntry)
                val viewmodel = remember {
                    PokemonListViewModel(
                        pokemonRepository = DefaultPokemonRepository(
                            remoteBookDataSource = KtorRemotePokemonDataSource(
                                httpClient = HttpClientFactory.create(engine = engine)
                            )
                        )
                    )
                }
                PokemonListScreenRoot(
                    pokemonListViewModel = viewmodel,
                    onPokemonClick = {
                        viewmodel.onAction(PokemonListAction.OnPokemonClick(it))
                        navController.navigate(PokemonRoute.PokemonDetails)
                    }
                )
            }

            composable<PokemonRoute.PokemonDetails> {
//                val viewModel: PokemonListViewModel =
//                    viewModel(navController.getBackStackEntry(PokemonRoute.PokemonListing))

                val viewmodel = remember {
                    PokemonListViewModel(
                        pokemonRepository = DefaultPokemonRepository(
                            remoteBookDataSource = KtorRemotePokemonDataSource(
                                httpClient = HttpClientFactory.create(engine = engine)
                            )
                        )
                    )
                }

                val selectedPokemon by viewmodel.selectedPokemon.collectAsStateWithLifecycle()

                selectedPokemon.pokemon?.let { pokemon ->
                    PokemonDetailScreen(pokemon = pokemon)
                }
            }

        }
    }
}