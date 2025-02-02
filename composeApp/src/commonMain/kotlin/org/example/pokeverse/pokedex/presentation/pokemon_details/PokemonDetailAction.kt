package org.example.pokeverse.pokedex.presentation.pokemon_details

sealed interface PokemonDetailAction {
    data object OnBackClick: PokemonDetailAction
    data object OnFavouriteClick: PokemonDetailAction
}