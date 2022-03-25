package com.example.bestrickandmorty.presentation.ui.fragments.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.bestrickandmorty.domain.episode.usecases.GetEpisodeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val getEpisodeListUseCase: GetEpisodeListUseCase) :
    ViewModel() {

    fun fetchEpisode() = getEpisodeListUseCase().cachedIn(viewModelScope)

}