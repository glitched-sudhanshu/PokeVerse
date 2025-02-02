package org.example.pokeverse.pokedex.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.pokeverse.core.domain.onError
import org.example.pokeverse.core.domain.onSuccess
import org.example.pokeverse.core.presentation.toUiText
import org.example.pokeverse.pokedex.domain.PokemonRepository
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailsState

class PokemonListViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state = _state.onStart {
            if (_state.value.pokemonsListing.isEmpty()) {
                getPokemonListing()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)

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

    private fun getPokemonListing() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            pokemonRepository.getPokemonListing(1, 700).onSuccess { pokemons ->
                    _state.update {
                        it.copy(isLoading = false, pokemonsListing = pokemons, errorMessage = null)
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            pokemonsListing = emptyList(),
                            errorMessage = error.toUiText()
                        )
                    }
                }
        }
    }
}