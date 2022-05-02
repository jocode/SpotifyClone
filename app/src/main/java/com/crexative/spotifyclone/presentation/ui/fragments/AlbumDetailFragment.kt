package com.crexative.spotifyclone.presentation.ui.fragments

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.data.models.albums.AlbumResponse
import com.crexative.spotifyclone.databinding.FragmentAlbumDetailBinding
import com.crexative.spotifyclone.presentation.ui.adapters.ItemTrackAdapter
import com.crexative.spotifyclone.presentation.viewmodels.AlbumDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

private val TAG: String = AlbumDetailFragment::class.java.simpleName

@AndroidEntryPoint
class AlbumDetailFragment : Fragment(R.layout.fragment_album_detail) {

    private lateinit var binding: FragmentAlbumDetailBinding
    private val viewModel: AlbumDetailViewModel by viewModels()
    private var albumId: String = ""
    private lateinit var itemTrackAdapter: ItemTrackAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumDetailBinding.bind(view)

        albumId = requireArguments().getString(ALBUM_ID, "")
        viewModel.getAlbum(albumId)
        observeAlbumState()
        setupView()
    }

    private fun setupView() = with(binding) {
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        rvTracks.apply {
            itemTrackAdapter = ItemTrackAdapter()
            adapter = itemTrackAdapter
        }

        itemTrackAdapter.setOnItemClickListener { item ->
            Log.e(TAG, "setupView: $item")
        }

    }

    private fun observeAlbumState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progressBar.isVisible = state.isLoading

            state.album?.let {
                showDataOnScreen(it)
            }
        }
    }

    private fun showDataOnScreen(album: AlbumResponse) = with(binding) {

        tvAlbumName.text = album.name

        val artists = album.artists.joinToString(", ") { it.name }
        val description = "${album.label} | $artists | ${album.totalTracks} tracks"
        tvAlbumDesc.text = description
        itemTrackAdapter.items = album.tracks.items

        Glide.with(requireContext())
            .load(album.images.first().url)
            .placeholder(R.drawable.ic_music_note)
            .into(imgAlbum)

        backDeg.background = linearGradientDrawable()

    }

    // method to generate linear gradient drawable
    private fun linearGradientDrawable(): GradientDrawable {
        return GradientDrawable().apply {
            colors = intArrayOf(
                Color.parseColor("#000000"),
                Color.parseColor("#2c2c2c"),
                Color.parseColor("#1DB954"),
                Color.parseColor("#008000"),
                Color.parseColor("#2c2c2c"),
            )
            gradientType = GradientDrawable.LINEAR_GRADIENT
            shape = GradientDrawable.RECTANGLE
            orientation = GradientDrawable.Orientation.BOTTOM_TOP

            // border around drawable
            // setStroke(5, Color.parseColor("#4B5320"))
        }
    }

    companion object {
        const val ALBUM_ID = "album_id"
    }
}