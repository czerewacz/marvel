package ai.akun.character_data

import ai.akun.character_data.mapper.toCharacterDomainEntity
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

class CharactersRepositoryTest : KoinTest {

    private val apiClient: ai.akun.character_data.remote.client.MarvelApiClient by inject()
    private val repository: ai.akun.character_data.repository.CharactersRepository by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<ai.akun.character_data.remote.client.MarvelApiClient> { mockk(relaxed = true) }
                single { ai.akun.character_data.repository.CharactersRepository(get()) }
            }
        )
    }

    @Test
    fun `should return character details`() = runTest {
        val response = TestResponse.baseResponseDto
        val expectedResult = response.data.results.first().toCharacterDomainEntity()

        //GIVEN
        coEvery {
            apiClient.getCharacterDetail("5")
        }.returns((flowOf(response)))

        //WHEN
        val actualResult = repository.getCharacterDetail("5").single()

        assertEquals(
            /* expected = */ expectedResult,
            /* actual = */ actualResult
        )
    }
}
