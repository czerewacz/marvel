package ai.akun.character_domain.di

import ai.akun.character_domain.useCases.GetCharacterDetailUseCase
import ai.akun.character_domain.useCases.GetCharactersUseCase
import org.koin.dsl.module

val characterDomainModule = module {

    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterDetailUseCase(get()) }

}