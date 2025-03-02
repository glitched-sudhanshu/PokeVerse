package org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.r02_sudhanshu.pokeverse.app.PokedexRoute
import org.r02_sudhanshu.pokeverse.pokedex.domain.PokemonRepository

class PokemonDetailViewModel(
    private val repository: PokemonRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonId = savedStateHandle.toRoute<PokedexRoute.PokemonDetails>().id

    private val _pokemonDetailsState = MutableStateFlow(PokemonDetailsState(pokemon = null))
    val pokemonDetailsState = _pokemonDetailsState.onStart {
        observeIsFavourite()
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _pokemonDetailsState.value)

    fun onAction(action: PokemonDetailAction) {
        when (action) {
            PokemonDetailAction.OnBackClick -> {}

            is PokemonDetailAction.OnFavouriteClick -> {
                viewModelScope.launch {
                    if (_pokemonDetailsState.value.isFavourite) {
                        repository.deleteFromFavorites(pokemonId)
                    } else {
                        repository.markAsFavorite(action.pokemon)
                    }
                }
            }

            is PokemonDetailAction.OnSelectedPokemonChange -> {
                _pokemonDetailsState.update {
                    it.copy(pokemon = action.pokemon)
                }
            }
        }
    }

    private fun observeIsFavourite() {
        viewModelScope.launch {
            repository.isPokemonFavorite(pokemonId).onEach { isFavourite ->
                _pokemonDetailsState.update {
                    it.copy(isFavourite = isFavourite)
                }
            }.launchIn(viewModelScope)
        }
    }
}