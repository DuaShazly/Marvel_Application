package com.example.marvelapplication.data.model.comics

data class ComicsResults(
    val description: String,
    val id: Int,
    val title: String,
//    val stories: List<MarvelComicsCollections>,
//    val events: List<MarvelComicsCollections>,
    val series: MarvelComicsCollections,
    val url: List<MarvelComicsURL>,
    val thumbnail: ComicsThumbnail
)