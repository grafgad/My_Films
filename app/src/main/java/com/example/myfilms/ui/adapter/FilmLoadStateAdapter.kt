package com.example.myfilms.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfilms.databinding.FilmLoadStateBinding

class FilmLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FilmLoadStateAdapter.LoadSateViewHolder>() {

    inner class LoadSateViewHolder(private val binding: FilmLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState is LoadState.Loading
                textViewError.isVisible = loadState is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(
        holder: LoadSateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadSateViewHolder =
        LoadSateViewHolder(
            FilmLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}