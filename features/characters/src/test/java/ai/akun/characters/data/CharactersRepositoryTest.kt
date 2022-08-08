package ai.akun.characters.data

import ai.akun.core.network.clients.MarvelApiClient
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

    private val apiClient: MarvelApiClient by inject()
    private val repository: CharactersRepository by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<MarvelApiClient> { mockk(relaxed = true) }
                single { CharactersRepository(get()) }
            }
        )
    }

    @Test
    fun `should return character details`() = runTest {
        val response = TestResponse.baseResponse

        //GIVEN
        coEvery {
            apiClient.getCharacterDetail("5")
        }.returns((flowOf(response)))

        assertEquals(response.data.results.first().toCharacterDomainEntity() , repository.getCharacterDetail("5").single())

    }
}