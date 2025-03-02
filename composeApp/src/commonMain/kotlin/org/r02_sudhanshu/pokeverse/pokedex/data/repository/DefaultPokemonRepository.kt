package org.r02_sudhanshu.pokeverse.pokedex.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.r02_sudhanshu.pokeverse.core.domain.DataError
import org.r02_sudhanshu.pokeverse.core.domain.EmptyResult
import org.r02_sudhanshu.pokeverse.core.domain.Result
import org.r02_sudhanshu.pokeverse.core.domain.map
import org.r02_sudhanshu.pokeverse.pokedex.data.database.FavoritePokemonDao
import org.r02_sudhanshu.pokeverse.pokedex.data.mappers.toPokemon
import org.r02_sudhanshu.pokeverse.pokedex.data.mappers.toPokemonEntity
import org.r02_sudhanshu.pokeverse.pokedex.data.network.RemotePokemonDataSource
import org.r02_sudhanshu.pokeverse.pokedex.domain.PokemonRepository
import org.r02_sudhanshu.pokeverse.pokedex.domain.model.Pokemon

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

    override fun isPokemonFavorite(id: Int): Flow<Boolean> {
        return favouritePokemonDao.getFavoritePokemons()
            .map { pokemons ->
                pokemons.any { it.id == id }
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

    override suspend fun deleteFromFavorites(id: Int): EmptyResult<DataError.Local> {
        return try {
            favouritePokemonDao.deleteFavoritePokemon(id)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}