package com.crexative.spotifyclone.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.albums.AlbumResponse
import com.crexative.spotifyclone.domain.usecases.GetAlbum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val getAlbumUseCase: GetAlbum
) : ViewModel() {

    private var _state = MutableLiveData(AlbumsState())
    val state: LiveData<AlbumsState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getAlbum(id: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.postValue(state.value?.copy(isLoading = true))


        when (val result = getAlbumUseCase(id)) {
            is Resource.Error -> {
                _state.postValue(state.value?.copy(isLoading = false))
                Log.e("TAG", "getNewReleases: ${result.message}")
                _eventFlow.emit(UIEvent.ShowSnackbar(result.message ?: "Unknown error happened"))
            }
            is Resource.Success -> {
                val albumResponse = result.data
                _state.postValue(
                    state.value?.copy(
                        album = albumResponse,
                        isLoading = false
                    )
                )
            }
        }

    }

    data class AlbumsState(
        val album: AlbumResponse? = null,
        val isLoading: Boolean = false
    )

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}