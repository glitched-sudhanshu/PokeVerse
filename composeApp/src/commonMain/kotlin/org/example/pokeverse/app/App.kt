package org.example.pokeverse.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
        Scaffold(
            floatingActionButton = {
                val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(
                    initialValue = null
                )
                val currentRoute = currentBackStackEntry?.destination?.route

                LaunchedEffect(currentRoute) {

                    println("if statement ${listOf(
                        PokedexRoute.PokemonListing.toString(),
                        PokedexRoute.PokemonDetails::class.qualifiedName
                    ).any { currentRoute?.contains(it ?: "") == true }}")

                    println("normal val ${PokedexRoute.PokemonListing.toString()}  ${PokedexRoute.PokemonDetails::class.qualifiedName}")

                    println("screen ${currentRoute}")
                }

                if (listOf(
                        PokedexRoute.PokemonListing.toString(),
                        PokedexRoute.PokemonDetails::class.qualifiedName
                    ).any { currentRoute?.contains(it ?: "") == true }
                ) {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate("specialFeatureEntry")
                        },
                        shape = CircleShape,
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Icon(Icons.Default.Star, contentDescription = "Special Feature")
                    }
                }
            }
        ) { internalPadding ->
            NavHost(
                navController = navController,
                startDestination = PokemonMainRoute.PokemonListingNavGraph,
                modifier = Modifier.padding(internalPadding)
            ) {
                navigation<PokemonMainRoute.PokemonListingNavGraph>(
                    startDestination = PokedexRoute.PokemonListing
                ) {
                    composable<PokedexRoute.PokemonListing>(
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
                                navController.navigate(PokedexRoute.PokemonDetails(it.id))
                            }
                        )
                    }

                    composable<PokedexRoute.PokemonDetails>(
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