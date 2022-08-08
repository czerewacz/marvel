package ai.akun.characters.domain.usecases

import ai.akun.characters.domain.ICharactersRepository
import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.core.usecase.UseCaseResult
import io.mockk.coEvery
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
                single<ICharactersRepository> { mockk(relaxed = true) }
            }
        )
    }

    @Test
    fun `should return success`() = runTest {
        val success = CharacterDomainEntity()

        //GIVEN
        coEvery {
            repository.getCharacterDetail("5")
        }.returns(flowOf(success))

        //THEN
        assertEquals(
            UseCaseResult.Success(success),
            getCharacterDetailUseCase("5").single()
        )
    }

}