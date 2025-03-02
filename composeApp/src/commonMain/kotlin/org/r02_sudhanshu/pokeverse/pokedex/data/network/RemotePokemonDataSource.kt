package org.r02_sudhanshu.pokeverse.pokedex.data.network

import org.r02_sudhanshu.pokeverse.core.domain.DataError
import org.r02_sudhanshu.pokeverse.core.domain.Result
import org.r02_sudhanshu.pokeverse.pokedex.data.dto.PokemonDto

interface RemotePokemonDataSource {

    suspend fun getPokemonListing(
        offset: Int,
        limit: Int = 20
    ): Result<List<PokemonDto>, DataError.Remote>

    suspend fun getPokemon(
        id: Int
    ): Result<PokemonDto, DataError.Remote>
}