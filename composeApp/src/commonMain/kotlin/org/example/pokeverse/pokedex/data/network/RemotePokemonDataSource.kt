package org.example.pokeverse.pokedex.data.network

import org.example.pokeverse.core.domain.DataError
import org.example.pokeverse.core.domain.Result
import org.example.pokeverse.pokedex.data.dto.PokemonDto

interface RemotePokemonDataSource {

    suspend fun getPokemonListing(
        offset: Int,
        limit: Int = 20
    ): Result<List<PokemonDto>, DataError.Remote>

    suspend fun getPokemon(
        id: Int
    ): Result<PokemonDto, DataError.Remote>
}