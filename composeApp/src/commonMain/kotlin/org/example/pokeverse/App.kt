package org.example.pokeverse

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.pokeverse.pokedex.presentation.PokemonRoute
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailScreen
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListAction
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreenRoot
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = PokemonRoute.PokemonListing
        ) {
            composable<PokemonRoute.PokemonListing> { backStackEntry ->
                val viewmodel: PokemonListViewModel = viewModel(backStackEntry)
                PokemonListScreenRoot(
                    pokemonListViewModel = viewmodel,
                    onPokemonClick = {
                        viewmodel.onAction(PokemonListAction.OnPokemonClick(it))
                        navController.navigate(PokemonRoute.PokemonDetails)
                    }
                )
            }

            composable<PokemonRoute.PokemonDetails> {
                val viewModel: PokemonListViewModel =
                    viewModel(navController.getBackStackEntry(PokemonRoute.PokemonListing))

                val selectedPokemon by viewModel.selectedPokemon.collectAsStateWithLifecycle()

                selectedPokemon.pokemon?.let { pokemon ->
                    PokemonDetailScreen(pokemon = pokemon)
                }
            }

        }
    }
}