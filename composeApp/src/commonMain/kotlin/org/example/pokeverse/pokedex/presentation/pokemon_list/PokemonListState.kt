package org.example.pokeverse.pokedex.presentation.pokemon_list

import org.example.pokeverse.core.presentation.UiText
import org.example.pokeverse.pokedex.domain.Pokemon
import org.example.pokeverse.pokedex.domain.PokemonType
import org.example.pokeverse.pokedex.presentation.pokemon_details.testPokemon

data class PokemonListState(
    val searchQuery: String? = null,
    val pokemonsListing: List<Pokemon> = pokemonList,
    val searchResult: List<Pokemon> = emptyList(),
    val favouritePokemons: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)

val pokemonList = (1..100).map {
    testPokemon.copy(id = it)
}