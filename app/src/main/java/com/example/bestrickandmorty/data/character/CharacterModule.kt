package com.example.bestrickandmorty.data.character

import com.example.bestrickandmorty.data.character.remote.CharacterApi
import com.example.bestrickandmorty.data.common.module.NetworkModule
import com.example.bestrickandmorty.domain.character.repository.CharacterDetailRepository
import com.example.bestrickandmorty.domain.character.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class CharacterModule {

    @Singleton
    @Provides
    fun providesCharacterApi(retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(characterApi: CharacterApi): CharacterRepository {
        return CharacterRepositoryImpl(characterApi)
    }

    @Singleton
    @Provides
    fun provideCharacterDetailRepository(characterApi: CharacterApi): CharacterDetailRepository {
        return CharacterRepositoryImpl(characterApi)
    }
}