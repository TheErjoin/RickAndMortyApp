package com.example.bestrickandmorty.domain.location.usecases

import com.example.bestrickandmorty.domain.location.repository.LocationRepository
import javax.inject.Inject

class GetLocationListUseCase @Inject constructor(private val repository: LocationRepository) {

    operator fun invoke(name: String?) = repository.getLocationList(name)

}