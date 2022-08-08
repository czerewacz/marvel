package ai.akun.characters.presentation

import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.characters.domain.usecases.GetCharactersUseCase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characters = MutableLiveData<PagingData<CharacterDomainEntity>>()
    val characters: LiveData<PagingData<CharacterDomainEntity>> = _characters

    fun getCharacters() {
        viewModelScope.launch {
            getCharactersUseCase().cachedIn(viewModelScope).collectLatest {
                _characters.postValue(it)
            }
        }
    }
}