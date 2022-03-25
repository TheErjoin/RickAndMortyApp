package com.example.bestrickandmorty.presentation.ui.fragments.characters.detail

import androidx.lifecycle.viewModelScope
import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.character.usecases.GetCharacterDetailDataUseCase
import com.example.bestrickandmorty.domain.common.base.BaseViewModel
import com.example.bestrickandmorty.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val getCharacterDetailDataUseCase: GetCharacterDetailDataUseCase) :
    BaseViewModel() {

    private val _stateCharacterDetailId =
        MutableStateFlow<UiState<CharacterEntity>>(UiState.Loading())
    val stateCharacterDetailId: StateFlow<UiState<CharacterEntity>> = _stateCharacterDetailId

    fun getDataCharacterDetail(id: Int) {
        viewModelScope.launch {
            loadData(_stateCharacterDetailId) {
                getCharacterDetailDataUseCase(id)
            }
        }
    }

}