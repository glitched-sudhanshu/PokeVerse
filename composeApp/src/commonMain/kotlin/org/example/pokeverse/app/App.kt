package org.example.pokeverse.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreenRoot
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokeDexViewModel
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListAction
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreenRoot
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
                    val viewmodel =
                        backStackEntry.sharedKoinViewModel<PokeDexViewModel>(navController)
                    PokemonListScreenRoot(
                        pokeDexViewModel = viewmodel,
                        onPokemonClick = {
                            viewmodel.onAction(PokemonListAction.OnPokemonClick(it))
                            navController.navigate(PokemonRoute.PokemonDetails)
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
                    }) { backStackEntry ->
                    val viewmodel =
                        backStackEntry.sharedKoinViewModel<PokeDexViewModel>(navController)
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