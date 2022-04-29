package com.crexative.spotifyclone.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.Item
import com.crexative.spotifyclone.data.models.NewReleasesResponse
import com.crexative.spotifyclone.domain.usecases.GetNewReleases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getNewReleaseUseCase: GetNewReleases
) : ViewModel() {

    private var _state = MutableLiveData<AlbumsState<Item>>(AlbumsState())
    val state: LiveData<AlbumsState<Item>> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getNewReleases()
    }

    private fun getNewReleases() = viewModelScope.launch(Dispatchers.IO) {
        _state.postValue(state.value?.copy(isLoading = true))

        when (val result = getNewReleaseUseCase()) {
            is Resource.Error -> {
                _state.postValue(state.value?.copy(isLoading = false))
                Log.e("TAG", "getNewReleases: ${result.message}")
            }
            is Resource.Success -> {
                val albums = result.data?.albums?.items ?: emptyList()
                _state.postValue(
                    state.value?.copy(
                        items = albums,
                        isLoading = false
                    )
                )
            }
        }

    }

    data class AlbumsState<T>(
        val items: List<T> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}