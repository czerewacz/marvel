package ai.akun.characters.domain.usecases

import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.characters.domain.ICharactersRepository
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val charactersRepository: ICharactersRepository
) {
    suspend operator fun invoke(): Flow<PagingData<CharacterDomainEntity>> {
        return charactersRepository.getCharacters()
    }
}