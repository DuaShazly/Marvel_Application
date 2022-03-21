package com.example.marvelapplication.data.model.characters

import com.example.marvelapplication.data.model.comics.MarvelComicsURL

data class MarvelCharactersResults(
    val description: String,
    val id: Int,
    val name: String,
//    val comics: List<MarvelCharactersCollections>,
//    val series: List<MarvelCharactersCollections>,
//    val events: List<MarvelCharactersCollections>,
    val thumbnail: MarvelCharactersThumbnail,
    val urls: List<MarvelComicsURL>,
    )
