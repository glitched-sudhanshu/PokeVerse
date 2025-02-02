package org.example.pokeverse.pokedex.data.repository

import org.example.pokeverse.core.domain.DataError
import org.example.pokeverse.core.domain.Result
import org.example.pokeverse.core.domain.map
import org.example.pokeverse.pokedex.data.mappers.toPokemon
import org.example.pokeverse.pokedex.data.network.RemotePokemonDataSource
import org.example.pokeverse.pokedex.domain.PokemonRepository
import org.example.pokeverse.pokedex.domain.model.Pokemon

class DefaultPokemonRepository(
    private val remoteBookDataSource: RemotePokemonDataSource
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
}