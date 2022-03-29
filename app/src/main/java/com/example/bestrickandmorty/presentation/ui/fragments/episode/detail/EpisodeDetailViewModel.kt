package com.example.bestrickandmorty.presentation.ui.fragments.episode.detail

import androidx.lifecycle.viewModelScope
import com.example.bestrickandmorty.domain.common.base.BaseViewModel
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity
import com.example.bestrickandmorty.domain.episode.usecases.GetEpisodeDetailUseCase
import com.example.bestrickandmorty.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase) :
    BaseViewModel() {

    private val _stateEpisodeDetailId =
        MutableStateFlow<UiState<EpisodeEntity>>(UiState.Loading())
    val stateEpisodeDetail: StateFlow<UiState<EpisodeEntity>> = _stateEpisodeDetailId

    fun getDataEpisodeDetail(id: Int) {
        viewModelScope.launch {
            loadData(_stateEpisodeDetailId) {
                getEpisodeDetailUseCase(id)
            }
        }
    }
}