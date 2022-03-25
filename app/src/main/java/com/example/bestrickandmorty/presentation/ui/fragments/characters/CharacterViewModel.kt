package com.example.bestrickandmorty.presentation.ui.fragments.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.character.usecases.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val getCharacterListUseCase: GetCharacterListUseCase) :
    ViewModel() {

    private val _characterList = MutableStateFlow<PagingData<CharacterEntity>?>(null)
    val characterList: StateFlow<PagingData<CharacterEntity>?> get() = _characterList

    init {
        fetchCharacter("")
    }

    fun fetchCharacter(
        name: String?,
    ) {
        viewModelScope.launch {
            getCharacterListUseCase(name).cachedIn(viewModelScope)
                .catch {
                    Log.e("Catch", "fetchCharacter: $it")
                }.collect {
                    _characterList.value = it
                }
        }
    }
}