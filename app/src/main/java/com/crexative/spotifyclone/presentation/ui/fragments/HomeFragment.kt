package com.crexative.spotifyclone.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.databinding.FragmentHomeBinding
import com.crexative.spotifyclone.presentation.ui.adapters.AlbumsAdapter
import com.crexative.spotifyclone.presentation.viewmodels.AlbumsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private val TAG: String = HomeFragment::class.java.simpleName

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val albumsViewModel: AlbumsViewModel by viewModels()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        setupRecycler()
        setupAlbumObserver()
        observeUIEvent()
    }

    private fun setupRecycler() = with(binding) {
        rvAlbums.apply {
            albumsAdapter = AlbumsAdapter()
            adapter = albumsAdapter
        }

        albumsAdapter.setOnItemClickListener { item ->
            Log.e(TAG, "setupView: $item")
            // Navigate another screen
        }
    }

    private fun observeUIEvent() = lifecycleScope.launch {
        albumsViewModel.eventFlow.collect { event ->
            when (event) {
                is AlbumsViewModel.UIEvent.ShowSnackbar -> {
                    Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupAlbumObserver() {
        albumsViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progressBar.isVisible = state.isLoading
            albumsAdapter.items = state.items
        }
    }
}