package com.example.myfilms.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myfilms.data.ApiService
import com.example.myfilms.databinding.ItemFilmBinding
import com.example.myfilms.model.Film

class FilmAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Film, FilmAdapter.FilmViewHolder>(FilmDiffCallback) {

    inner class FilmViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(film: Film) {
            with(binding) {
                title.text = film.title
                image.load("${ApiService.imageUrl}${film.image}")
                date.text = film.date
                ratingText.text = film.voteCount.toString()
                ratingProgress.progress = film.rating.toInt()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(film: Film)
    }

    object FilmDiffCallback : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }
    }

}