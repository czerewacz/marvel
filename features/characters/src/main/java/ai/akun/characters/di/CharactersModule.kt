package ai.akun.characters.di

import ai.akun.characters.data.CharactersRepository
import ai.akun.characters.domain.ICharactersRepository
import ai.akun.characters.domain.usecases.GetCharacterDetailUseCase
import ai.akun.characters.domain.usecases.GetCharactersUseCase
import ai.akun.characters.presentation.CharacterDetailViewModel
import ai.akun.characters.presentation.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {

    single<ICharactersRepository> { CharactersRepository(get()) }

    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterDetailUseCase(get()) }

    viewModel { CharactersViewModel(get()) }
    viewModel { params -> CharacterDetailViewModel(params.get(), get()) }
}