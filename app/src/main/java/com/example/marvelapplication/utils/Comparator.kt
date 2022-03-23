package com.example.marvelapplication.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.marvelapplication.data.model.characters.MarvelCharactersResults


object Comparator {

    val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<MarvelCharactersResults>() {
        override fun areItemsTheSame(oldItem: MarvelCharactersResults, newItem: MarvelCharactersResults): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarvelCharactersResults, newItem: MarvelCharactersResults): Boolean {
            return oldItem.name == newItem.name
        }
    }



}