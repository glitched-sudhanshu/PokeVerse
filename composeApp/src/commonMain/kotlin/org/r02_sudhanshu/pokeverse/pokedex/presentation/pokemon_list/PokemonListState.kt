package org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list

import org.r02_sudhanshu.pokeverse.core.presentation.UiText
import org.r02_sudhanshu.pokeverse.pokedex.domain.model.Pokemon
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details.testPokemon

data class PokemonListState(
    val searchQuery: String? = null,
    val pokemonsListing: List<Pokemon> = emptyList(),
    val searchResult: List<Pokemon> = emptyList(),
    val favouritePokemons: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isNextPageLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)

val pokemonList = (1..100).map {
    testPokemon.copy(id = it)
}