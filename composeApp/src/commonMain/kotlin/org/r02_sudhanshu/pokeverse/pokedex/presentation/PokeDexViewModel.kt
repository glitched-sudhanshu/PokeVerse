package org.r02_sudhanshu.pokeverse.pokedex.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.r02_sudhanshu.pokeverse.pokedex.domain.model.Pokemon
import org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_details.PokemonDetailsState

class PokeDexViewModel : ViewModel() {
    private val _selectedPokemon = MutableStateFlow(PokemonDetailsState(null))
    val selectedPokemon = _selectedPokemon.asStateFlow()

    fun setSelectedPokemon(pokemon: Pokemon?) {
        _selectedPokemon.update {
            it.copy(pokemon = pokemon)
        }
    }
}