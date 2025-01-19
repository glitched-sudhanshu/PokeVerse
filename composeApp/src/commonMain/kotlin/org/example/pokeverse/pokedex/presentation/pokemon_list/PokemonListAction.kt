package org.example.pokeverse.pokedex.presentation.pokemon_list

import org.example.pokeverse.pokedex.domain.Pokemon

sealed interface PokemonListAction {
    data class OnSearchQueryChange(val query: String?): PokemonListAction
    data class OnPokemonClick(val pokemon: Pokemon): PokemonListAction
    data class OnTabSelected(val index: Int): PokemonListAction
    data class OnPokemonLikeClick(val pokemon: Pokemon): PokemonListAction
}