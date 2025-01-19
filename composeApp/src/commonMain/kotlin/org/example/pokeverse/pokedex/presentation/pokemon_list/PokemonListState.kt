package org.example.pokeverse.pokedex.presentation.pokemon_list

import org.example.pokeverse.core.presentation.UiText
import org.example.pokeverse.pokedex.domain.Pokemon

data class PokemonListState(
    val searchQuery: String? = null,
    val searchResult: List<Pokemon> = emptyList(),
    val favouritePokemons: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)