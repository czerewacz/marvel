package ai.akun.character_domain.model

/**
 * Data class for Character
 */
data class CharacterDomainEntity(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val thumbnail: String = "",
    val comics: List<String> = emptyList(),
)
