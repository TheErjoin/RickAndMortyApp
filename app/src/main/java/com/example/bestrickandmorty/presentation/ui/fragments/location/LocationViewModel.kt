package com.example.bestrickandmorty.presentation.ui.fragments.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bestrickandmorty.domain.location.model.LocationEntity
import com.example.bestrickandmorty.domain.location.usecases.GetLocationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val getLocationListUseCase: GetLocationListUseCase) :
    ViewModel() {

    private val _locationList = MutableStateFlow<PagingData<LocationEntity>?>(null)
    val locationList: StateFlow<PagingData<LocationEntity>?> get() = _locationList

    init {
        fetchLocation()
    }

    fun fetchLocation(name: String? = null) {
        viewModelScope.launch {
            getLocationListUseCase(name).cachedIn(viewModelScope).collect {
                _locationList.value = it
            }
        }
    }

}