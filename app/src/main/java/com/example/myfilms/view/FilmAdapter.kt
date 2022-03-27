package com.example.myfilms.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myfilms.R
import com.example.myfilms.model.Film
import com.example.myfilms.data.Films
import com.example.myfilms.databinding.ItemMovieBinding

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private var filmList = emptyList<Film>()

    fun setData() {
        filmList = Films.localFilms
    }

    class FilmViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image = binding.image//view.findViewById<ImageView>(R.id.image)
        private val title = binding.title//view.findViewById<TextView>(R.id.title)
        private val date = binding.date//view.findViewById<TextView>(R.id.image)
        fun bind(film: Film) {
            title.text = film.title
            image.setImageResource(film.image)
            date.text = film.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmList[position])
    }

    override fun getItemCount() = filmList.size
}