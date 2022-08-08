package ai.akun.core.network.di

import ai.akun.core.BuildConfig
import ai.akun.core.network.Endpoints
import ai.akun.core.network.Endpoints.GET_CHARACTERS
import ai.akun.core.network.Endpoints.GET_CHARACTER_ID
import ai.akun.core.network.Endpoints.PROD_URL
import ai.akun.core.network.clients.MarvelApiClient
import ai.akun.core.network.clients.ProdHttpClient
import org.koin.dsl.module

val networkModule = module {
    single {
        MarvelApiClient(
            httpClient = ProdHttpClient().httpClient,
            baseUrl = PROD_URL,
            apiKey = Endpoints.API_KEY,
            hashKey = BuildConfig.HASH_KEY,
            getCharactersEndpoint = GET_CHARACTERS,
            getCharacterIdEndpoint = GET_CHARACTER_ID,
            backgroundDispatcher = get()
        )
    }
}