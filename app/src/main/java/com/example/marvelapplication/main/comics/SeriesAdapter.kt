package com.example.marvelapplication.main.comics

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.characters.MarvelCharacterCollectionItems
import com.example.marvelapplication.data.model.comics.ComicsResults
import com.example.marvelapplication.main.comics.ComicsAdapter.ComicsViewHolder
import com.example.marvelapplication.main.comics.SeriesAdapter.SeriesViewHolder
import com.example.marvelapplication.utils.GlideUtils


class SeriesAdapter(private var comics: List<MarvelCharacterCollectionItems>)
    : RecyclerView.Adapter<SeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater .from(parent.context)
            .inflate(R.layout.item_comics, parent, false)

        return SeriesViewHolder(view)
    }
    override fun getItemCount(): Int {
        return comics.size
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    fun updateComics(comics: List<MarvelCharacterCollectionItems>) {
        this.comics = comics
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        this.comics = listOf()
        notifyDataSetChanged()
    }

    inner class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgComic : ImageView = itemView.findViewById(R.id.imgComic)
        private val textComicName : TextView = itemView.findViewById(R.id.textComicName)

        @SuppressLint("SetTextI18n")
        fun bind(comic: MarvelCharacterCollectionItems) {

//            comic.thumbnail.run {
//                GlideUtils.urlToImageView(imgComic.context, "$path.$extension",imgComic)
//            }
//
//            textComicName.text=comic.title
        }
    }
}