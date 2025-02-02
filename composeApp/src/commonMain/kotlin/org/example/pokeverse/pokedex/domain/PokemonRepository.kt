package org.example.pokeverse.pokedex.domain

import kotlinx.coroutines.flow.Flow
import org.example.pokeverse.core.domain.DataError
import org.example.pokeverse.core.domain.EmptyResult
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
    
    fun getFavoritePokemons(): Flow<List<Pokemon>>
    fun isPokemonFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(pokemon: Pokemon): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: String): EmptyResult<DataError.Local>
}