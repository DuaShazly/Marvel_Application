package com.example.marvelapplication.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.characters.MarvelCharactersResults
import com.example.marvelapplication.utils.GlideUtils
import com.example.marvelapplication.main.home.CharactersAdapter.CharactersViewHolder
import com.example.marvelapplication.utils.Comparator.CHARACTER_COMPARATOR

class CharactersAdapter(private val onCharacterClick: (character: MarvelCharactersResults) -> Unit)
    : PagedListAdapter<MarvelCharactersResults, CharactersViewHolder>(CHARACTER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater .from(parent.context)
            .inflate(R.layout.item_characters, parent, false)

        return CharactersViewHolder(view)
    }


    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgCharacter : ImageView = itemView.findViewById(R.id.imgCharacter)
        private val textName : TextView = itemView.findViewById(R.id.textName)

        @SuppressLint("SetTextI18n")
        fun bind(character: MarvelCharactersResults) {

            itemView.setOnClickListener {
                onCharacterClick.invoke(character)
            }

            character.thumbnail.run {
                GlideUtils.urlToImageView(imgCharacter.context, "$path.$extension",imgCharacter)
            }

            textName.text=character.name
        }
    }

}