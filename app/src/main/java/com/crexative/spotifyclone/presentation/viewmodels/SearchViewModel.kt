package com.crexative.spotifyclone.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.domain.usecases.SearchSongsAndArtist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchSongsAndArtist: SearchSongsAndArtist
) : ViewModel() {

    private var _state = MutableLiveData<SearchState<Any>>(SearchState())
    val state: LiveData<SearchState<Any>> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onSearch(query: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.postValue(state.value?.copy(isLoading = true))

        when (val result = searchSongsAndArtist(query)) {
            is Resource.Error -> {
                _state.postValue(state.value?.copy(isLoading = false))
            }
            is Resource.Success -> {
                val tracks = result.data?.tracks?.items ?: emptyList()
                val artist = result.data?.artists?.items ?: emptyList()

                _state.postValue(
                    state.value?.copy(
                        isLoading = false,
                        tracks = tracks,
                        artists = artist
                    )
                )
            }
        }

    }

    data class SearchState<T>(
        val tracks: List<T> = emptyList(),
        val artists: List<T> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}