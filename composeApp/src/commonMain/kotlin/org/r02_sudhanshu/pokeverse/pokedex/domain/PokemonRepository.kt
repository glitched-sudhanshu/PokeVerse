package org.r02_sudhanshu.pokeverse.pokedex.domain

import kotlinx.coroutines.flow.Flow
import org.r02_sudhanshu.pokeverse.core.domain.DataError
import org.r02_sudhanshu.pokeverse.core.domain.EmptyResult
import org.r02_sudhanshu.pokeverse.core.domain.Result
import org.r02_sudhanshu.pokeverse.pokedex.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonListing(
        offset: Int,
        limit: Int
    ): Result<List<Pokemon>, DataError.Remote>

    suspend fun getPokemon(
        id: Int
    ): Result<Pokemon, DataError.Remote>
    
    fun getFavoritePokemons(): Flow<List<Pokemon>>
    fun isPokemonFavorite(id: Int): Flow<Boolean>
    suspend fun markAsFavorite(pokemon: Pokemon): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: Int): EmptyResult<DataError.Local>
}