package com.example.bestrickandmorty.data.search

import com.example.bestrickandmorty.data.common.module.NetworkModule
import com.example.bestrickandmorty.data.search.remote.SearchApi
import com.example.bestrickandmorty.domain.search.repository.SearchCharacterRepository
import com.example.bestrickandmorty.domain.search.repository.SearchEpisodeRepository
import com.example.bestrickandmorty.domain.search.repository.SearchLocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Singleton
    @Provides
    fun providesSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    fun providesSearchCharacterRepository(searchApi: SearchApi): SearchCharacterRepository {
        return SearchRepositoryImpl(searchApi)
    }

    @Singleton
    @Provides
    fun providesSearchEpisodeRepository(searchApi: SearchApi): SearchEpisodeRepository {
        return SearchRepositoryImpl(searchApi)
    }

    @Singleton
    @Provides
    fun providesSearchLocationRepository(searchApi: SearchApi): SearchLocationRepository {
        return SearchRepositoryImpl(searchApi)
    }
}