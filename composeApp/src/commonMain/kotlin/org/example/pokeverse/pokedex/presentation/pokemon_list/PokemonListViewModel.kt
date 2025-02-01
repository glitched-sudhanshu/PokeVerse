package org.example.pokeverse.pokedex.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailsState

class PokemonListViewModel : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state = _state.asStateFlow()

    private val _selectedPokemon = MutableStateFlow(PokemonDetailsState(null))
    val selectedPokemon = _selectedPokemon.asStateFlow()

    fun onAction(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.OnPokemonClick -> {
                _selectedPokemon.update {
                    it.copy(pokemon = action.pokemon)
                }
            }

            is PokemonListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is PokemonListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }

            is PokemonListAction.OnPokemonLikeClick -> {}
        }
    }
}