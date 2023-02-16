package ai.akun.character_presentation.di

import ai.akun.character_presentation.ui.characters.CharactersViewModel
import ai.akun.character_presentation.ui.details.CharacterDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterPresentationModule = module {

    viewModel { CharactersViewModel(get()) }
    viewModel { params -> CharacterDetailViewModel(params.get(), get()) }
}