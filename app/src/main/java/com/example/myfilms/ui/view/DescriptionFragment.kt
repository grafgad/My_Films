package com.example.myfilms.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.myfilms.data.ApiService
import com.example.myfilms.databinding.FragmentDescriptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionFragment : Fragment(/*R.layout.fragment_description*/) {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DescriptionFragmentArgs>()

      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDescriptionBinding.bind(view)
        binding.apply {
            val film = args.film
            image.load("${ApiService.imageUrl}${film.image}")
            title.text = film.title
            date.text = film.date
            overview.text = film.overview
            ratingProgress.progress = film.rating.toInt()
            ratingText.text = film.voteCount.toString()

        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}