package org.example.pokeverse.pokedex.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.pokeverse.core.data.safeCall
import org.example.pokeverse.core.domain.DataError
import org.example.pokeverse.core.domain.Result

private const val BASE_URL = "https://pokeapi.co/api/v2"

class KtorRemotePokemonDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getPokemon(
        query: String,
        offset: Int,
        limit: Int = 20,
    ): Result<Any, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/pokemon"
            ) {
                parameter("limit", limit)
                parameter("offset", offset)
            }
        }
    }
}