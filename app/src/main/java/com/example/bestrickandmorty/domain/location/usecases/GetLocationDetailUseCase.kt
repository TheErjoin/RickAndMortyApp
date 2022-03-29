package com.example.bestrickandmorty.domain.location.usecases

import com.example.bestrickandmorty.domain.location.repository.LocationDetailRepository
import javax.inject.Inject

class GetLocationDetailUseCase @Inject constructor(private val repository: LocationDetailRepository) {

    operator fun invoke(id: Int) = repository.getLocationDetail(id)

}