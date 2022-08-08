package ai.akun.characters.domain.usecases

import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.characters.domain.ICharactersRepository
import ai.akun.core.usecase.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetCharacterDetailUseCase(
    private val charactersRepository: ICharactersRepository
) {
    suspend operator fun invoke(id: String): Flow<UseCaseResult<CharacterDomainEntity>> {
        return charactersRepository.getCharacterDetail(id).map { character ->
            UseCaseResult.Success(character) as UseCaseResult<CharacterDomainEntity>
        }.catch { error ->
            emit(UseCaseResult.Error(error))
        }
    }
}