package ai.akun.core.network.clients

import ai.akun.core.network.error.ErrorResponse
import ai.akun.core.network.error.NetworkFailure
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ProdHttpClient {

    val httpClient = HttpClient(CIO) {

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

        expectSuccess = false

        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                when (statusCode) {
                    in 300..399 -> throw NetworkFailure.Redirect("Gateway error")
                    in 400..499 -> throw NetworkFailure.ClientError(
                        response.status.value,
                        response.body<ErrorResponse>().message
                    )
                    in 500..599 -> throw NetworkFailure.ServerError(
                        response.status.value,
                        response.body<ErrorResponse>().message
                    )
                }
                if (statusCode >= 600) {
                    throw NetworkFailure.UnknownError("Unknown Network error")
                }
            }
        }
    }
}