package ai.akun.characters.data

import ai.akun.characters.domain.ICharactersRepository
import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.core.network.clients.MarvelApiClient
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersRepository(
    private val apiClient: MarvelApiClient
) : ICharactersRepository {
    override suspend fun getCharacters(): Flow<PagingData<CharacterDomainEntity>> = Pager(
        PagingConfig(
            pageSize = CHARACTERS_LOAD_SIZE,
            maxSize = 90,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            CharactersPagingSource(
                apiClient = apiClient
            )
        }
    ).flow

    override suspend fun getCharacterDetail(characterId: String): Flow<CharacterDomainEntity> {
        return apiClient.getCharacterDetail(characterId).map { baseResponse ->
            baseResponse.data.results.first().toCharacterDomainEntity()
        }
    }
}

