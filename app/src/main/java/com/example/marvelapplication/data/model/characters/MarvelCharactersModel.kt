package com.example.marvelapplication.data.model.characters

import com.example.marvelapplication.data.model.characters.MarvelCharactersData

data class MarvelCharactersModel(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: MarvelCharactersData,
    val etag: String,
    val status: String
)