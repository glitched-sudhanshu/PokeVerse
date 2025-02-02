package org.example.pokeverse.pokedex.domain

import org.example.pokeverse.core.domain.DataError
import org.example.pokeverse.core.domain.Result
import org.example.pokeverse.pokedex.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonListing(
        offset: Int,
        limit: Int
    ): Result<List<Pokemon>, DataError.Remote>

    suspend fun getPokemon(
        id: Int
    ): Result<Pokemon, DataError.Remote>
}