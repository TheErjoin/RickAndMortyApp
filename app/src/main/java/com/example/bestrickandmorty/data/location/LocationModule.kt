package com.example.bestrickandmorty.data.location

import com.example.bestrickandmorty.data.common.module.NetworkModule
import com.example.bestrickandmorty.data.location.remote.LocationApi
import com.example.bestrickandmorty.domain.location.repository.LocationDetailRepository
import com.example.bestrickandmorty.domain.location.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class LocationModule {

    @Singleton
    @Provides
    fun providesLocationApi(retrofit: Retrofit): LocationApi {
        return retrofit.create(LocationApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationRepository(locationApi: LocationApi): LocationRepository {
        return LocationRepositoryImpl(locationApi)
    }

    @Singleton
    @Provides
    fun provideLocationDetailRepository(locationApi: LocationApi): LocationDetailRepository {
        return LocationRepositoryImpl(locationApi)
    }
}