package com.crexative.spotifyclone.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.core.hide
import com.crexative.spotifyclone.core.visible
import com.crexative.spotifyclone.data.models.search.Item
import com.crexative.spotifyclone.data.models.search.ItemTrack
import com.crexative.spotifyclone.databinding.FragmentSearchBinding
import com.crexative.spotifyclone.presentation.ui.adapters.ArtistAdapter
import com.crexative.spotifyclone.presentation.ui.adapters.SearchItemConcatAdapter
import com.crexative.spotifyclone.presentation.ui.adapters.TrackAdapter
import com.crexative.spotifyclone.presentation.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

private val TAG: String = SearchFragment::class.java.simpleName

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by activityViewModels()

    private val tracksAdapter = TrackAdapter()
    private val artistAdapter = ArtistAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        handleSearchView()
        observeSearch()
    }

    private fun handleSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewModel.onSearch(query)
                Log.e("TAG", "onQueryTextSubmit: $query")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.e(TAG, "onQueryTextChange: $newText")
                return false
            }
        })
    }

    private fun observeSearch() = with(binding) {

        searchViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progressBar.isVisible = state.isLoading

            tracksAdapter.items = state.tracks as List<ItemTrack>
            artistAdapter.items = state.artists as List<Item>

            if (state.tracks.isNotEmpty()) {
                imgSearch.hide()
                val concatAdapter = ConcatAdapter()
                concatAdapter.apply {
                    addAdapter(0, SearchItemConcatAdapter(getString(R.string.songs), tracksAdapter))
                    addAdapter(
                        1,
                        SearchItemConcatAdapter(getString(R.string.artists), artistAdapter)
                    )
                }
                rvSearch.adapter = concatAdapter
            } else {
                imgSearch.visible()
            }
        }

    }
}