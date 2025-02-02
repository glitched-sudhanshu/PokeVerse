package org.example.pokeverse.pokedex.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.example.pokeverse.core.domain.DataError
import org.example.pokeverse.core.domain.EmptyResult
import org.example.pokeverse.core.domain.Result
import org.example.pokeverse.core.domain.map
import org.example.pokeverse.pokedex.data.database.FavoritePokemonDao
import org.example.pokeverse.pokedex.data.mappers.toPokemon
import org.example.pokeverse.pokedex.data.mappers.toPokemonEntity
import org.example.pokeverse.pokedex.data.network.RemotePokemonDataSource
import org.example.pokeverse.pokedex.domain.PokemonRepository
import org.example.pokeverse.pokedex.domain.model.Pokemon

class DefaultPokemonRepository(
    private val remoteBookDataSource: RemotePokemonDataSource,
    private val favouritePokemonDao: FavoritePokemonDao
) : PokemonRepository {

    override suspend fun getPokemonListing(
        offset: Int,
        limit: Int
    ): Result<List<Pokemon>, DataError.Remote> {
        return remoteBookDataSource
            .getPokemonListing(offset = offset, limit = limit)
            .map { dto ->
                dto.map { it.toPokemon() }
            }
    }

    override suspend fun getPokemon(
        id: Int
    ): Result<Pokemon, DataError.Remote> =
        remoteBookDataSource.getPokemon(id).map { dto -> dto.toPokemon() }

    override fun getFavoritePokemons(): Flow<List<Pokemon>> {
        return favouritePokemonDao.getFavoritePokemons()
            .map { pokemons ->
                pokemons.map { it.toPokemon() }
            }
    }

    override fun isPokemonFavorite(id: String): Flow<Boolean> {
        return flow {
            emit(favouritePokemonDao.getFavoritePokemon(id) != null)
        }
    }

    override suspend fun markAsFavorite(pokemon: Pokemon): EmptyResult<DataError.Local> {
        return try {
            favouritePokemonDao.upsert(pokemon.toPokemonEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String): EmptyResult<DataError.Local> {
        return try {
            favouritePokemonDao.deleteFavoritePokemon(id)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}