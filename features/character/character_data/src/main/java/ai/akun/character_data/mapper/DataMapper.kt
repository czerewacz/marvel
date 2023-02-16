package ai.akun.character_data.mapper

import ai.akun.character_data.remote.dto.CharacterResponseDto
import ai.akun.character_domain.model.CharacterDomainEntity


fun CharacterResponseDto.toCharacterDomainEntity() =
    CharacterDomainEntity(
        id,
        name,
        description = if (description == "") "Description not Available" else description,
        thumbnail = "${this.thumbnail.path}/portrait_uncanny.${this.thumbnail.extension}",
        comics = comics.items.map { comic -> comic.name }
    )

