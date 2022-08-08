package ai.akun.core.network.mock

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

val mockClient = HttpClient(MockEngine) {

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }

    engine {
        addHandler { request ->
            when (request.url.encodedPath) {
                "/v1/public/characters" -> {
                    respond(
                        Json.encodeToString(MockResponse.baseResponse),
                        HttpStatusCode.OK,
                        responseHeaders
                    )
                }
                else -> {
                    error("Unhandled ${request.url.encodedPath}")
                }
            }
        }
    }
}