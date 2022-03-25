package com.example.bestrickandmorty.presentation.ui.fragments.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.bestrickandmorty.domain.location.usecases.GetLocationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val getLocationListUseCase: GetLocationListUseCase) :
    ViewModel() {

    fun fetchLocation() = getLocationListUseCase().cachedIn(viewModelScope)

}