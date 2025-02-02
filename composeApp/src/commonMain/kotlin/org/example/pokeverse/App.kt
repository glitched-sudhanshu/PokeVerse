package org.example.pokeverse

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.pokeverse.pokedex.presentation.PokemonRoute
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreen
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListAction
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreenRoot
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = PokemonRoute.PokemonNavGraph
        ) {
            navigation<PokemonRoute.PokemonNavGraph>(
                startDestination = PokemonRoute.PokemonListing
            ) {
                composable<PokemonRoute.PokemonListing> { backStackEntry ->
                    val viewmodel =
                        backStackEntry.sharedKoinViewModel<PokemonListViewModel>(navController)
                    PokemonListScreenRoot(
                        pokemonListViewModel = viewmodel,
                        onPokemonClick = {
                            viewmodel.onAction(PokemonListAction.OnPokemonClick(it))
                            navController.navigate(PokemonRoute.PokemonDetails)
                        }
                    )
                }

                composable<PokemonRoute.PokemonDetails> { backStackEntry ->
                    val viewmodel =
                        backStackEntry.sharedKoinViewModel<PokemonListViewModel>(navController)

                    val selectedPokemon by viewmodel.selectedPokemon.collectAsStateWithLifecycle()

                    selectedPokemon.pokemon?.let { pokemon ->
                        PokemonDetailScreen(pokemon = pokemon)
                    }
                }

            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}