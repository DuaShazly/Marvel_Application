package com.example.marvelapplication.di


import com.example.marvelapplication.data.repository.MarvelRepository
import com.example.marvelapplication.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    private val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
    private val hash = Utils.md5(ts+ com.example.marvelapplication.utils.Constants.API_PRIVATE_KEY+ com.example.marvelapplication.utils.Constants.API_PUBLIC_KEY)

    @Singleton
    @Provides
    fun provideMarvelRepository(): MarvelRepository {
        return MarvelRepository()
    }



}