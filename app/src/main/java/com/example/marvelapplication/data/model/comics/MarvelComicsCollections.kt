package com.example.marvelapplication.data.model.comics


data class MarvelComicsCollections(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<MarvelComicsCollectionItems>,


)