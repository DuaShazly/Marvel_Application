package com.example.marvelapplication.data.model.characters

data class MarvelCharactersResults(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: MarvelCharactersThumbnail
)