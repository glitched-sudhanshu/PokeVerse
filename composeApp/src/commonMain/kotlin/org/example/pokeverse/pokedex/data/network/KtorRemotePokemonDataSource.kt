package org.example.pokeverse.pokedex.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.example.pokeverse.core.data.safeCall
import org.example.pokeverse.core.domain.DataError
import org.example.pokeverse.core.domain.Result
import org.example.pokeverse.pokedex.data.dto.PokemonDto

private const val BASE_URL = "https://pokeapi.co/api/v2"

class KtorRemotePokemonDataSource(
    private val httpClient: HttpClient
) : RemotePokemonDataSource {
    override suspend fun getPokemonListing(
        offset: Int,
        limit: Int,
    ): Result<List<PokemonDto>, DataError.Remote> = coroutineScope {
        val deferredResults = (offset until (offset + limit)).map { id ->
            async { getPokemon(id) }
        }
        val results = deferredResults.awaitAll()

        val successList = results.filterIsInstance<Result.Success<PokemonDto>>().map { it.data }
        if (successList.size == results.size) {
            Result.Success(successList)
        } else {
            val errorResult =
                results.filterIsInstance<Result.Error<DataError.Remote>>()
                    .map { it.error }.firstOrNull()
            Result.Error(errorResult ?: DataError.Remote.SERVER)
        }
    }

    override suspend fun getPokemon(id: Int): Result<PokemonDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/pokemon/$id"
            ) {
            }
        }
    }
}