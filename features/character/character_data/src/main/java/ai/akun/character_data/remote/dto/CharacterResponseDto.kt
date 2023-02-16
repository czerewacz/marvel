package ai.akun.character_data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Marvel API character network response item.
 *
 * @param id The unique ID of the character resource.
 * @param name The name of the character.
 * @param description A short bio or description of the character.
 * @param thumbnail The representative image for this character.
 */
@Serializable
data class CharacterResponseDto(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: CharacterThumbnailResponseDto,
    val comics: ComicsResponseDto
)
