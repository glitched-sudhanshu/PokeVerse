package org.example.pokeverse.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import org.example.pokeverse.pokedex.presentation.PokeDexViewModel
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailAction
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreenRoot
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailViewModel
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreenRoot
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListingViewModel
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
                composable<PokemonRoute.PokemonListing>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) { backStackEntry ->
                    val sharedViewModel =
                        backStackEntry.sharedKoinViewModel<PokeDexViewModel>(navController)
                    val viewmodel = koinViewModel<PokemonListingViewModel>()

                    LaunchedEffect(true) {
                        sharedViewModel.setSelectedPokemon(null)
                    }

                    PokemonListScreenRoot(
                        pokemonListingViewModel = viewmodel,
                        onPokemonClick = {
                            sharedViewModel.setSelectedPokemon(it)
                            navController.navigate(PokemonRoute.PokemonDetails(it.id))
                        }
                    )
                }

                composable<PokemonRoute.PokemonDetails>(
                    enterTransition = {
                        slideInHorizontally { initialOffset ->
                            initialOffset
                        }
                    },
                    exitTransition = {
                        slideOutHorizontally { initialOffset ->
                            initialOffset
                        }
                    }
                ) { backStackEntry ->
                    val sharedViewModel =
                        backStackEntry.sharedKoinViewModel<PokeDexViewModel>(navController)
                    val viewmodel = koinViewModel<PokemonDetailViewModel>()

                    val selectedPokemon by sharedViewModel.selectedPokemon.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedPokemon) {
                        selectedPokemon.pokemon?.let {
                            viewmodel.onAction(PokemonDetailAction.OnSelectedPokemonChange(it))
                        }
                    }

                    PokemonDetailScreenRoot(
                        viewModel = viewmodel,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
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