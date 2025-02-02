package org.example.pokeverse.pokedex.presentation.pokemon_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PokemonDetailViewModel: ViewModel() {

    private val _pokemonDetailsState = MutableStateFlow(PokemonDetailsState(pokemon = null))
    val pokemonDetailsState = _pokemonDetailsState.asStateFlow()

    fun onAction(action: PokemonDetailAction) {
        when(action) {
            PokemonDetailAction.OnBackClick -> {}

            PokemonDetailAction.OnFavouriteClick -> {

            }
            is PokemonDetailAction.OnSelectedPokemonChange -> {
                _pokemonDetailsState.update {
                    it.copy(pokemon = action.pokemon)
                }
            }
        }
    }
}