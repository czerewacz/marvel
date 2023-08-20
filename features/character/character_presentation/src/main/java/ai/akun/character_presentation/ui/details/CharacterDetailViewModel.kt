package ai.akun.character_presentation.ui.details

import ai.akun.character_domain.model.CharacterDomainEntity
import ai.akun.character_domain.useCases.GetCharacterDetailUseCase
import ai.akun.core.usecase.UseCaseResult
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    state: SavedStateHandle,
    private val getCharacterDetail: GetCharacterDetailUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _characterId = MutableLiveData<String>()
    val characterId: LiveData<String> = _characterId

    private val _character = MutableLiveData<CharacterDomainEntity>()
    val character: LiveData<CharacterDomainEntity> = _character

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        state.get<String>("id")?.let { setCharacterId(it) }
    }

    private fun setCharacterId(id: String) {
        _characterId.postValue(id)
    }

    fun getCharacter(id: String) {
        _uiState.postValue(UIState.Loading)
        viewModelScope.launch {
            getCharacterDetail(id).collect { result ->
                if (result is UseCaseResult.Success) {
                    _uiState.postValue(UIState.Success)
                    _character.postValue(result.data)
                } else if (result is UseCaseResult.Error) {
                    _error.postValue(result.exception.localizedMessage)
                }
            }
        }
    }

    sealed class UIState {
        object Loading : UIState()
        object Success : UIState()
        object Error : UIState()
    }
}