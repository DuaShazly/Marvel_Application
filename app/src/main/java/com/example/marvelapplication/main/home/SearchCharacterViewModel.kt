package com.example.marvelapplication.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelapplication.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchCharacterViewModel @Inject constructor(private val marvelRepository: MarvelRepository) :
    ViewModel() {

    private val searchQuery = MutableStateFlow("")

    val searchResult = searchQuery.flatMapLatest { query ->
        marvelRepository.searchCharacter2(query).cachedIn(viewModelScope)
    }.asLiveData()







}