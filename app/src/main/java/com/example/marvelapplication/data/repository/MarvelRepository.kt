package com.example.marvelapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.marvelapplication.data.model.characters.MarvelCharactersModel
import com.example.marvelapplication.data.model.comics.ComicsModel
import com.example.marvelapplication.data.remote.Resource
import com.example.marvelapplication.data.remote.ServiceClientInstance
import com.example.marvelapplication.main.home.CharacterPagingSource
import com.example.marvelapplication.utils.Constants
import com.example.marvelapplication.utils.Utils
import com.example.marvelapplication.utils.Utils.safeApiCall
import java.util.*

class MarvelRepository {

    private val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
    private val hash = Utils.md5(ts+ Constants.API_PRIVATE_KEY+ Constants.API_PUBLIC_KEY)

    suspend fun getCharacters(offset: Int): Resource<MarvelCharactersModel> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.getCharacters(
            ts = ts, hash = hash, offset = offset
        ) })
    }

    suspend fun getComics(characterId: Int, dateRange: String): Resource<ComicsModel> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.getComics(
            ts = ts, hash = hash, characterId = characterId, dateRange = dateRange
        ) })
    }


    suspend fun searchCharacter(name:String): Resource<MarvelCharactersModel> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.searchCharacter(
            ts = ts, hash = hash, url = name
        ) })
    }


    fun searchCharacter2(query: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { CharacterPagingSource( ServiceClientInstance.getInstance().api, query) }
    ).flow

}