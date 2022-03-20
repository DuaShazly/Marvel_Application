package com.example.marvelapplication.data.model.comics

data class ComicsResults(
    val description: String,
    val id: Int,
    val thumbnail: ComicsThumbnail,
    val title: String,
)