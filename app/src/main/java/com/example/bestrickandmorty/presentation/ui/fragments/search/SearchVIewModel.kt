package com.example.bestrickandmorty.presentation.ui.fragments.search

import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.common.base.BaseViewModel
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity
import com.example.bestrickandmorty.domain.location.model.LocationEntity
import com.example.bestrickandmorty.domain.search.usecases.GetSearchingCharacterUseCase
import com.example.bestrickandmorty.domain.search.usecases.GetSearchingEpisodeUseCase
import com.example.bestrickandmorty.domain.search.usecases.GetSearchingLocationUseCase
import com.example.bestrickandmorty.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchVIewModel @Inject constructor(
    private val getCharacterListUseCase: GetSearchingCharacterUseCase,
    private val getLocationListUseCase: GetSearchingLocationUseCase,
    private val getEpisodeListUseCase: GetSearchingEpisodeUseCase
) : BaseViewModel() {

    private val _fetchCharacterSearch =
        MutableStateFlow<UiState<List<CharacterEntity>>>(UiState.Loading())
    val fetchCharacterSearch: StateFlow<UiState<List<CharacterEntity>>>
        get() =
            _fetchCharacterSearch

    fun fetchCharacter(name: String) {
        loadData(_fetchCharacterSearch) {
            getCharacterListUseCase.invoke(name)
        }
    }

    private val _fetchLocationSearch =
        MutableStateFlow<UiState<List<LocationEntity>>>(UiState.Loading())
    val fetchLocationSearch: StateFlow<UiState<List<LocationEntity>>> get() = _fetchLocationSearch

    fun fetchLocations(name: String) {
        loadData(_fetchLocationSearch) {
            getLocationListUseCase.invoke(name)
        }
    }

    private val _fetchEpisodeSearch =
        MutableStateFlow<UiState<List<EpisodeEntity>>>(UiState.Loading())
    val fetchEpisodeSearch: StateFlow<UiState<List<EpisodeEntity>>> get() = _fetchEpisodeSearch

    fun fetchEpisodes(name: String) {
        loadData(_fetchEpisodeSearch) {
            getEpisodeListUseCase(name)
        }
    }
}