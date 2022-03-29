package com.example.bestrickandmorty.data.episode

import com.example.bestrickandmorty.data.common.module.NetworkModule
import com.example.bestrickandmorty.data.episode.remote.EpisodeApi
import com.example.bestrickandmorty.domain.episode.repository.EpisodeDetailRepository
import com.example.bestrickandmorty.domain.episode.repository.EpisodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class EpisodeModule {

    @Singleton
    @Provides
    fun provideEpisodeApi(retrofit: Retrofit): EpisodeApi {
        return retrofit.create(EpisodeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEpisodeRepository(api: EpisodeApi): EpisodeRepository {
        return EpisodeRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideEpisodeDetailRepository(api: EpisodeApi): EpisodeDetailRepository {
        return EpisodeRepositoryImpl(api)
    }
}