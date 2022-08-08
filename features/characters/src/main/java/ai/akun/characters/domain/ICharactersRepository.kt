package ai.akun.characters.domain

import ai.akun.characters.domain.model.CharacterDomainEntity
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    suspend fun getCharacters(): Flow<PagingData<CharacterDomainEntity>>

    suspend fun getCharacterDetail(characterId : String) : Flow<CharacterDomainEntity>
}