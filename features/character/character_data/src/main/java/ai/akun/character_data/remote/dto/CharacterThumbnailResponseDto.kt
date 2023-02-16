package ai.akun.character_data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Marvel API character thumbnail network response.
 *
 * @param path The directory path of to the image.
 * @param extension The file extension for the image.
 */
@Serializable
data class CharacterThumbnailResponseDto(
    val path: String,
    val extension: String
)
