package ai.akun.characters.data

import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.core.network.responses.CharacterResponse

fun CharacterResponse.toCharacterDomainEntity() = CharacterDomainEntity(
    id,
    name,
    description = if (description == "") "Description not Available" else description,
    thumbnail = "${this.thumbnail.path}/portrait_uncanny.${this.thumbnail.extension}",
    comics = comics.items.map { comic -> comic.name }
)

