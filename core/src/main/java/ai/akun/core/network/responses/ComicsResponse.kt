package ai.akun.core.network.responses

import kotlinx.serialization.Serializable

/**
 * Marvel API Comics network response item.
 *
 * @param available Amount of comics available.
 * @param items The list of comics.
 */
@Serializable
data class ComicsResponse(
    val available: Int,
    val items: List<Comic>
)

/**
 * @param name Comic name.
 */
@Serializable
data class Comic(
    val name: String,
)
