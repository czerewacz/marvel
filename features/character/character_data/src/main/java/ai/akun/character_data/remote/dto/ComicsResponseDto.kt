package ai.akun.character_data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Marvel API Comics network response item.
 *
 * @param available Amount of comics available.
 * @param items The list of comics.
 */
@Serializable
data class ComicsResponseDto(
    val available: Int,
    val items: List<ComicDto>
)

/**
 * @param name Comic name.
 */
@Serializable
data class ComicDto(
    val name: String,
)
