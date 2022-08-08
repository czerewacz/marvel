package ai.akun.core.network.error

import kotlinx.serialization.Serializable

/**
 * Serializable for handling error responses from API
 */
@Serializable
class ErrorResponse(
    val code: String? = "400",
    val message: String = ""
)