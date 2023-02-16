package ai.akun.character_domain.useCases

import ai.akun.character_domain.model.CharacterDomainEntity
import ai.akun.character_domain.reposiroty.ICharactersRepository
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val charactersRepository: ICharactersRepository
) {
    suspend operator fun invoke(): Flow<PagingData<CharacterDomainEntity>> {
        return charactersRepository.getCharacters()
    }
}