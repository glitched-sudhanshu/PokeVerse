package org.example.pokeverse

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreenRoot
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        PokemonListScreenRoot(
            pokemonListViewModel = remember { PokemonListViewModel() },
            onPokemonClick = {}
        )
    }
}