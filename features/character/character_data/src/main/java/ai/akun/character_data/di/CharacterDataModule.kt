package ai.akun.character_data.di

import ai.akun.character_data.remote.CharacterEndpoints.GET_CHARACTERS
import ai.akun.character_data.remote.CharacterEndpoints.GET_CHARACTER_ID
import ai.akun.character_data.repository.CharactersRepository
import ai.akun.character_domain.reposiroty.ICharactersRepository
import ai.akun.core.BuildConfig
import ai.akun.core.network.CoreNetwork
import ai.akun.core.network.clients.ProdHttpClient
import org.koin.dsl.module

val characterDataModule = module {

    single<ICharactersRepository> { CharactersRepository(get()) }

    single {
        ai.akun.character_data.remote.client.MarvelApiClient(
            httpClient = ProdHttpClient().httpClient,
            baseUrl = CoreNetwork.PROD_URL,
            apiKey = CoreNetwork.API_KEY,
            hashKey = BuildConfig.HASH_KEY,
            getCharactersEndpoint = GET_CHARACTERS,
            getCharacterIdEndpoint = GET_CHARACTER_ID,
            backgroundDispatcher = get()
        )
    }
}