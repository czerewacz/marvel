package ai.akun.character_data.remote.client

import ai.akun.character_data.remote.dto.BaseResponseDto
import ai.akun.character_data.remote.dto.CharacterResponseDto
import ai.akun.core.network.error.NetworkFailure
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import kotlin.coroutines.CoroutineContext

/**
 * Ktor client class with [CIO] for making network requests
 */

class MarvelApiClient(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val apiKey: String,
    private val hashKey: String,
    private val getCharactersEndpoint: String,
    private val getCharacterIdEndpoint: String,
    private val backgroundDispatcher: CoroutineContext
) {

    private val timestamp = "1"

    /**
     * Get a list of marvel characters
     */

    fun getCharacters(
        limit: Int,
        offset: Int
    ): Flow<BaseResponseDto<CharacterResponseDto>> {
        return flow<BaseResponseDto<CharacterResponseDto>> {
            emit(
                httpClient.request() {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = baseUrl
                        port = 443
                        path(getCharactersEndpoint)
                    }
                    method = HttpMethod.Get
                    contentType(ContentType.Application.Json)
                    parameter("ts", timestamp)
                    parameter("apikey", apiKey)
                    parameter("hash", hashKey)
                    parameter("limit", limit)
                    parameter("offset", offset)
                }.body()
            )
        }.flowOn(
            backgroundDispatcher
        ).retry(retries = 3) { error ->
            if (error is NetworkFailure.Redirect) {
                val seconds = (2000).toLong()
                delay(seconds)
                return@retry true
            } else {
                return@retry false

            }
        }
    }


    /**
     * Get a list of marvel characters
     */

    fun getCharacterDetail(id: String): Flow<BaseResponseDto<CharacterResponseDto>> {
        return flow<BaseResponseDto<CharacterResponseDto>> {
            emit(
                httpClient.request() {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = baseUrl
                        port = 443
                        path(String.format(getCharacterIdEndpoint, id))
                    }
                    method = HttpMethod.Get
                    contentType(ContentType.Application.Json)
                    parameter("ts", timestamp)
                    parameter("apikey", apiKey)
                    parameter("hash", hashKey)
                }.body()
            )
        }.flowOn(
            backgroundDispatcher
        ).retry(retries = 3) { error ->
            if (error is NetworkFailure.Redirect) {
                val seconds = (2000).toLong()
                delay(seconds)
                return@retry true
            } else {
                return@retry false
            }
        }
    }
}