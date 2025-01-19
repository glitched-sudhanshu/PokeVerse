package org.example.pokeverse.previews.pokemon_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokeverse.pokedex.domain.Ability
import org.example.pokeverse.pokedex.domain.Move
import org.example.pokeverse.pokedex.domain.Pokemon
import org.example.pokeverse.pokedex.domain.PokemonType
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListScreen
import org.example.pokeverse.pokedex.presentation.pokemon_list.PokemonListState

@Preview
@Composable
private fun PokemonListScreenPreview(modifier: Modifier = Modifier) {
    PokemonListScreen(
        state = PokemonListState(
            searchQuery = "",
            searchResult = emptyList(),
            pokemonsListing = pokemonList,
            selectedTabIndex = 0,
            favouritePokemons = emptyList(),
            isLoading = false,
            errorMessage = null
        ),
        onAction = {}
    )
}


val pokemonList = (1..100).map {
    Pokemon(
        types = listOf(PokemonType.Fairy),
        name = "clefairy",
        id = "$it",
        height = 12,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        moves = listOf(Move("pound")),
        weight = 13,
        abilities = listOf(Ability("friend-guard")),
        baseExperience = 124,
    )
}