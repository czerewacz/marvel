package ai.akun.character_domain.useCases


import ai.akun.character_domain.model.CharacterDomainEntity
import ai.akun.character_domain.reposiroty.ICharactersRepository
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