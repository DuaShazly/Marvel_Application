package com.example.marvelapplication.main.comics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapplication.data.model.comics.ComicsModel
import com.example.marvelapplication.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(private val repository: MarvelRepository) : ViewModel() {

    val postComics: MutableLiveData<ComicsModel> by lazy {
        MutableLiveData()
    }

    fun getComics(characterId: Int, dateRange: String){
        viewModelScope.launch {
            val retrofitPost = repository.getComics(characterId,dateRange)
            retrofitPost.data?.let {
                postComics.postValue(it)
                Log.d("comics_response",it.toString())
            }
        }
    }
}