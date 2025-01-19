package org.example.pokeverse.pokedex.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PokemonListViewModel : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state = _state.asStateFlow()

    fun onAction(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.OnPokemonClick -> {

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
        }
    }
}