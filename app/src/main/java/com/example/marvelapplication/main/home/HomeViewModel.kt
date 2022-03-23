package com.example.marvelapplication.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.marvelapplication.data.model.characters.MarvelCharactersResults
import com.example.marvelapplication.data.model.comics.ComicsModel
import com.example.marvelapplication.data.repository.MarvelDataSource
import com.example.marvelapplication.data.repository.MarvelRepository
import com.example.marvelapplication.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MarvelRepository) : ViewModel() {

    private var charactersLiveData  : LiveData<PagedList<MarvelCharactersResults>>

    private val post: MutableLiveData<MarvelCharactersResults> by lazy {
        MutableLiveData()
    }

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.LOAD_SIZE_HINT)
            .setEnablePlaceholders(true)
            .build()
        charactersLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getCharacters(): LiveData<PagedList<MarvelCharactersResults>> = charactersLiveData


    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, MarvelCharactersResults> {

        val dataSourceFactory = object : DataSource.Factory<String, MarvelCharactersResults>() {
            override fun create(): DataSource<String, MarvelCharactersResults> {
                return MarvelDataSource(Dispatchers.IO,repository)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    fun search(name: String){

        viewModelScope.launch {
            val retrofitPost = repository.searchCharacter(name)
            retrofitPost.data?.let {
//                post.postValue(it)
                Log.d("search_response",it.toString())
            }

        }
    }
}