package com.example.marvelapplication.data.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.marvelapplication.data.model.characters.MarvelCharactersResults
import com.example.marvelapplication.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MarvelDataSource(coroutineContext: CoroutineContext,
private val repository: MarvelRepository
) :PageKeyedDataSource<String, MarvelCharactersResults>() {

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, MarvelCharactersResults>) {
        scope.launch {
            val response = repository.getCharacters(offset = 0)
            response.data?.let { responseData ->
                Log.d("marvelpage","page: "+"1"+",loadsize: "+params.requestedLoadSize+
                        ",total: "+responseData.data.results.size)
                callback.onResult(responseData.data.results, null, (Constants.FIRST_PAGE+1).toString())
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, MarvelCharactersResults>) {
        scope.launch {
                val page = params.key
                val numberOfItems = params.requestedLoadSize * page.toInt()
                val response = repository.getCharacters(offset = numberOfItems)
                response.data?.let { responseData ->
                    Log.d("marvelpage","page: "+page+",loadsize: "+numberOfItems+
                            ",total: "+responseData.data.results.size)
                    callback.onResult(responseData.data.results, (page.toInt()+1).toString())
                }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, MarvelCharactersResults>) {
        scope.launch {
            val page = params.key
            val numberOfItems = params.requestedLoadSize * page.toInt()
            val response = repository.getCharacters(offset = numberOfItems)
            response.data?.let { responseData ->
                Log.d("marvelpage","page: "+page+",loadsize: "+numberOfItems+
                        ",total: "+responseData.data.results.size)
                callback.onResult(responseData.data.results, (page.toInt()-1).toString())
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}