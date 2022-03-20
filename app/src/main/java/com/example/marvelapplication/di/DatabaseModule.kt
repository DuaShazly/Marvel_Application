package com.example.marvelapplication.di

import com.example.marvelapplication.data.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMarvelRepository(): MarvelRepository {
        return MarvelRepository()
    }
}