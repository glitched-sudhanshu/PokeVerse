package org.example.pokeverse.pokedex.presentation.pokemon_list

import org.example.pokeverse.core.presentation.UiText
import org.example.pokeverse.pokedex.domain.Ability
import org.example.pokeverse.pokedex.domain.Move
import org.example.pokeverse.pokedex.domain.Pokemon
import org.example.pokeverse.pokedex.domain.PokemonType

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