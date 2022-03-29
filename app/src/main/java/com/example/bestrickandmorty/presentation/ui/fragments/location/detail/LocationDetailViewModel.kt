package com.example.bestrickandmorty.presentation.ui.fragments.location.detail

import androidx.lifecycle.viewModelScope
import com.example.bestrickandmorty.domain.common.base.BaseViewModel
import com.example.bestrickandmorty.domain.location.model.LocationEntity
import com.example.bestrickandmorty.domain.location.usecases.GetLocationDetailUseCase
import com.example.bestrickandmorty.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(private val getLocationDetailUseCase: GetLocationDetailUseCase) :
    BaseViewModel() {

    private val _stateLocationDetailId =
        MutableStateFlow<UiState<LocationEntity>>(UiState.Loading())
    val stateLocationDetail: StateFlow<UiState<LocationEntity>> = _stateLocationDetailId

    fun getDataLocationDetail(id: Int) {
        viewModelScope.launch {
            loadData(_stateLocationDetailId) {
                getLocationDetailUseCase(id)
            }
        }
    }

}