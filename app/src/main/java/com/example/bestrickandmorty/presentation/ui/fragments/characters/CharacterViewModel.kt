package com.example.bestrickandmorty.presentation.ui.fragments.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.character.usecases.GetCharacterListUseCase
import com.example.bestrickandmorty.domain.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val getCharacterListUseCase: GetCharacterListUseCase) :
    BaseViewModel() {

    private val _characterList = MutableStateFlow<PagingData<CharacterEntity>?>(null)
    val characterList: StateFlow<PagingData<CharacterEntity>?> get() = _characterList

    private val _state =
        MutableStateFlow<CharacterFragmentState>(CharacterFragmentState.Init)
    val state: StateFlow<CharacterFragmentState> get() = _state

    init {
        fetchCharacter()
    }

    fun fetchCharacter(
        name: String? = null,
        status: String? = null,
        gender: String? = null
    ) {
        viewModelScope.launch {
            getCharacterListUseCase(name, status, gender).cachedIn(viewModelScope)
                .catch {
                    setLoading()
                    showToast(it.message)
                }.collect {
                    setLoading()
                    _characterList.value = it
                }
        }
    }
    private fun showToast(message: String?) {
        _state.value = CharacterFragmentState.ShowToast(message)
    }

    private fun setLoading() {
        _state.value = CharacterFragmentState.IsLoading {}
    }

    sealed class CharacterFragmentState {
        object Init : CharacterFragmentState()
        data class IsLoading(val request: suspend () -> Unit) : CharacterFragmentState()
        data class ShowToast(val message: String?) : CharacterFragmentState()

    }

}