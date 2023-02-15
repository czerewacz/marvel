package ai.akun.characters.domain.usecases

import ai.akun.characters.domain.ICharactersRepository
import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.core.usecase.UseCaseResult
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class GetCharacterDetailUseCaseTest : KoinTest {

    private val getCharacterDetailUseCase: GetCharacterDetailUseCase by inject()
    private val repository: ICharactersRepository by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                factory { GetCharacterDetailUseCase(get()) }
                single<ICharactersRepository> { mockk(relaxed = false) }
            }
        )
    }

    @Test
    fun `should return Character result`() = runTest {
        val expectedCharacter = CharacterDomainEntity()
        val characterId = "5"

        //GIVEN
        every {
            repository.getCharacterDetail(characterId = characterId)
        } returns flowOf(expectedCharacter)

        //WHEN
        val actualResult = getCharacterDetailUseCase(characterId).single()

        //THEN
        assertEquals(
            /* expected = */ UseCaseResult.Success(expectedCharacter),
            /* actual = */ actualResult
        )
    }
}
