package ai.akun.character_domain.reposiroty

import ai.akun.character_domain.model.CharacterDomainEntity
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    fun getCharacters(): Flow<PagingData<CharacterDomainEntity>>

    fun getCharacterDetail(characterId : String) : Flow<CharacterDomainEntity>
}
