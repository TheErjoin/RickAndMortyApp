package com.example.bestrickandmorty.presentation.ui.fragments.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity
import com.example.bestrickandmorty.domain.episode.usecases.GetEpisodeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val getEpisodeListUseCase: GetEpisodeListUseCase) :
    ViewModel() {

    private val _episodeList = MutableStateFlow<PagingData<EpisodeEntity>?>(null)
    val episodeList: StateFlow<PagingData<EpisodeEntity>?> get() = _episodeList

    init {
        fetchEpisode()
    }

    fun fetchEpisode(name: String? = null, episode: String? = null) {
        viewModelScope.launch {
            getEpisodeListUseCase(name, episode).cachedIn(viewModelScope).collect {
                _episodeList.value = it
            }
        }
    }

}