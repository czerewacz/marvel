package ai.akun.core.network.clients

import ai.akun.core.network.Endpoints
import ai.akun.core.network.mock.MockResponse
import ai.akun.core.network.mock.mockClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class MarvelApiClientTest : KoinTest {

    private val apiClient: MarvelApiClient by inject()
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single {
                    MarvelApiClient(
                        httpClient = mockClient,
                        backgroundDispatcher = testDispatcher,
                        apiKey = "",
                        hashKey = "",
                        baseUrl = Endpoints.PROD_URL,
                        getCharactersEndpoint = Endpoints.GET_CHARACTERS,
                        getCharacterIdEndpoint = Endpoints.GET_CHARACTER_ID
                    )
                }
            }
        )
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `get characters data and return 200`() = runTest {
        apiClient.getCharacters(limit = 0, offset = 0).collect { response ->
            assertEquals(MockResponse.baseResponse, response)
        }
    }
}