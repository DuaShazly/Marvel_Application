package com.example.marvelapplication.data.model.comics


data class MarvelComicsCollections(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<MarvelComicsCollectionItems>,
//    val series: List<MarvelComicsCollections>,
//    val events: List<MarvelComicsCollections>

)