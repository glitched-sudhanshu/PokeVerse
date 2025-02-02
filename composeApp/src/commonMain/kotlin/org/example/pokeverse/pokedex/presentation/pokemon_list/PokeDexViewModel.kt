package org.example.pokeverse.pokedex.presentation.pokemon_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.pokeverse.core.domain.onError
import org.example.pokeverse.core.domain.onSuccess
import org.example.pokeverse.core.presentation.toUiText
import org.example.pokeverse.pokedex.domain.PokemonRepository
import org.example.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailsState
import org.example.pokeverse.pokedex.presentation.pokemon_list.utils.AppConstants

@OptIn(FlowPreview::class)
class PokeDexViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state = _state.onStart {
        if (_state.value.pokemonsListing.isEmpty()) {
            getPokemonListing()
            observeQueryChange()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)

    private var searchPokemonJob: Job? = null

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

    private var currentPage = 0
    private var endReached = mutableStateOf(false)

    fun getPokemonListing() {
        viewModelScope.launch {
            if (endReached.value || !_state.value.searchQuery.isNullOrBlank()) {
                return@launch
            }
            _state.update {
                it.copy(isLoading = currentPage == 0, isNextPageLoading = currentPage > 0)
            }
            pokemonRepository.getPokemonListing(
                limit = AppConstants.PAGE_SIZE,
                offset = currentPage * AppConstants.PAGE_SIZE + 1
            ).onSuccess { pokemons ->
                endReached.value =
                    currentPage * AppConstants.PAGE_SIZE >= AppConstants.TOTAL_POKEMONS
                _state.update {
                    it.copy(
                        isLoading = false,
                        pokemonsListing = it.pokemonsListing + pokemons,
                        errorMessage = null,
                        isNextPageLoading = false
                    )
                }
                currentPage++
            }.onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        pokemonsListing = emptyList(),
                        errorMessage = if (currentPage == 0) error.toUiText() else null,
                        isNextPageLoading = false
                    )
                }
            }
        }
    }

    private fun observeQueryChange() {
        viewModelScope.launch {
            _state
                .map { it.searchQuery }
                .distinctUntilChanged()
                .debounce(500L)
                .onEach { query ->
                    when {
                        query.isNullOrBlank() -> {}

                        else -> {
                            searchPokemonJob?.cancel()
                            searchPokemonJob = searchPokemons(query)
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun searchPokemons(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true, isNextPageLoading = false, searchResult = emptyList())
        }
        val searchResults =
            _state.value.pokemonsListing.filter { it.name.lowercase().contains(query.lowercase()) }
        _state.update {
            it.copy(isLoading = false, isNextPageLoading = false, searchResult = searchResults)
        }
    }
}