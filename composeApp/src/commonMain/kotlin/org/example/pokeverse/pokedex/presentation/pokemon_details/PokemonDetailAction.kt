package org.example.pokeverse.pokedex.presentation.pokemon_details

import org.example.pokeverse.pokedex.domain.model.Pokemon

sealed interface PokemonDetailAction {
    data object OnBackClick : PokemonDetailAction
    data class OnFavouriteClick(val pokemon: Pokemon) : PokemonDetailAction
    data class OnSelectedPokemonChange(val pokemon: Pokemon) : PokemonDetailAction
}