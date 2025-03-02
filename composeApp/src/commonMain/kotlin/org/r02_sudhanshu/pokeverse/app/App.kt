package org.r02_sudhanshu.pokeverse.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.r02_sudhanshu.pokeverse.arena.presentation.ArenaViewModel
import org.r02_sudhanshu.pokeverse.arena.presentation.RootBattleGroundScreen
import org.r02_sudhanshu.pokeverse.core.presentation.AudioViewModel
import org.r02_sudhanshu.pokeverse.pokedex.presentation.PokeDexViewModel
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailAction
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreenRoot
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailViewModel
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreenRoot
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.PokemonListingViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.ic_pokemon_battle

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

                if (listOf(
                        PokedexRoute.PokemonListing.toString(),
                        PokedexRoute.PokemonDetails::class.qualifiedName
                    ).any { currentRoute?.contains(it ?: "") == true }
                ) {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(PokemonMainRoute.PokemonArenaNavGraph)
                        },
                        shape = CircleShape,
                        containerColor = Color.White,
                        contentColor = Color.White
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_pokemon_battle),
                            contentDescription = "Special Feature",
                            modifier = Modifier.size(48.dp)
                        )
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
                        val audioViewModel = koinViewModel<AudioViewModel>()
                        LaunchedEffect(selectedPokemon) {
                            selectedPokemon.pokemon?.let {
                                viewmodel.onAction(PokemonDetailAction.OnSelectedPokemonChange(it))
                            }
                        }

                        PokemonDetailScreenRoot(
                            viewModel = viewmodel,
                            audioViewModel = audioViewModel,
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }

                navigation<PokemonMainRoute.PokemonArenaNavGraph>(
                    startDestination = ArenaRoute.BattleGround(
                        ground = "https://i.pinimg.com/originals/e9/55/5b/e9555b52cf2e05d1e0e8a5fb6001d6ed.jpg",
                        firstPlayer = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/7.gif",
                        secondPlayer = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/female/25.gif"
                    )
                ) {
                    composable<ArenaRoute.BattleGround> {
                        val arenaViewModel = koinViewModel<ArenaViewModel>()
                        val audioViewModel = koinViewModel<AudioViewModel>()
                        CompositionLocalProvider(LocalNavController provides navController) {
                            RootBattleGroundScreen(
                                modifier = Modifier.fillMaxSize(),
                                viewModel = arenaViewModel,
                                audioViewModel = audioViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("No NavController provided")
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

//firstPlayer = "https://static.wikia.nocookie.net/project-pokemon/images/3/3c/250px-007Squirtle.png/revision/latest/thumbnail/width/360/height/360?cb=20160917031328",
//secondPlayer = "https://unite.pokemon.com/images/pokemon/pikachu/stat/stat-pikachu.png"