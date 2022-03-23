package com.example.marvelapplication.data.model.characters


data class MarvelCharactersCollections(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<MarvelCharacterCollectionItems>,
)