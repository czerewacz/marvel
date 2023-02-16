package ai.akun.character_data.repository

import ai.akun.character_data.mapper.toCharacterDomainEntity
import ai.akun.character_data.remote.client.MarvelApiClient
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersRepository(
    private val apiClient: MarvelApiClient
) : ai.akun.character_domain.reposiroty.ICharactersRepository {
    override fun getCharacters(): Flow<PagingData<ai.akun.character_domain.model.CharacterDomainEntity>> =
        Pager(
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

    override fun getCharacterDetail(characterId: String): Flow<ai.akun.character_domain.model.CharacterDomainEntity> {
        return apiClient.getCharacterDetail(characterId).map { baseResponse ->
            baseResponse.data.results.first().toCharacterDomainEntity()
        }
    }
}

