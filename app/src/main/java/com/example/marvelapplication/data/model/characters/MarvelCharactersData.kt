package com.example.marvelapplication.data.model.characters

data class MarvelCharactersData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<MarvelCharactersResults>,
    val total: Int
)