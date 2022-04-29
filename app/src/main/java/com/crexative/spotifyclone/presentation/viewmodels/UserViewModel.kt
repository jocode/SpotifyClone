package com.crexative.spotifyclone.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.playlist.Item
import com.crexative.spotifyclone.domain.usecases.GetUserPlaylist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserPlaylist: GetUserPlaylist
) : ViewModel() {

    private var _state = MutableLiveData<SearchState<Item>>(SearchState())
    val state: LiveData<SearchState<Item>> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getPlaylist()
    }

    fun getPlaylist() = viewModelScope.launch(Dispatchers.IO) {
        _state.postValue(state.value?.copy(isLoading = true))

        when (val result = getUserPlaylist()) {
            is Resource.Error -> {
                _state.postValue(state.value?.copy(isLoading = false))
            }
            is Resource.Success -> {
                val tracks = result.data?.items ?: emptyList()

                _state.postValue(
                    state.value?.copy(
                        isLoading = false,
                        playlist = tracks,
                    )
                )
            }
        }

    }

    data class SearchState<T>(
        val playlist: List<T> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}