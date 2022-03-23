package com.example.marvelapplication.main.home

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapplication.data.model.characters.MarvelCharactersResults
import com.example.marvelapplication.data.remote.ServiceApi
import com.example.marvelapplication.utils.Constants
import com.example.marvelapplication.utils.Utils

import retrofit2.HttpException
import java.util.*



class CharacterPagingSource(
    private val marvelApi: ServiceApi,
    private val query: String
) : PagingSource<Int, MarvelCharactersResults>() {

    private val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
    private val hash = Utils.md5(ts+ Constants.API_PRIVATE_KEY+ Constants.API_PUBLIC_KEY)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharactersResults> {
        val position = (params.key ?: Constants.FIRST_PAGE)
        return try {

             val characters  = if (query != "") {
                val response = marvelApi.searchCharacter2(query = query, offset = position, limit = params.loadSize)
                val data = response.body()!!.data
                data.results
            } else {
                val response = marvelApi.getCharacters( ts = ts, hash = hash, offset = position)
                val data = response.body()!!.data
                data.results
            }

            LoadResult.Page(
                data = characters,
                prevKey = if (position == Constants.FIRST_PAGE) null else position - Constants.PAGE_SIZE,
                nextKey = if (characters?.isEmpty() == true) null else position + Constants.PAGE_SIZE
            )
        }catch (exception: Exception) {
            Log.i("CHARACTER EXCEPTION", exception.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MarvelCharactersResults>): Int? {
        TODO("Not yet implemented")
    }
}