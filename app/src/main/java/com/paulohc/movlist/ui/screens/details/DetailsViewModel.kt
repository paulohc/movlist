package com.paulohc.movlist.ui.screens.details

import androidx.lifecycle.*
import com.paulohc.movlist.domain.MovieInfo
import com.paulohc.movlist.navigation.ARG_MOVIE_ID
import com.paulohc.movlist.network.MovieService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailsScreenState(
    val movieDetails: MovieInfo? = null,
    val failedToFetchData: Boolean = false,
)

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val service: MovieService,
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state.asStateFlow()

    private val movieId: Int = checkNotNull(savedStateHandle[ARG_MOVIE_ID])

    init {
        fetchMovieDetails()
    }

    private fun fetchMovieDetails() {
        viewModelScope.launch {
            val result = service.getMovieDetails(movieId)
            _state.update {
                it.copy(
                    movieDetails = result.getOrNull(),
                    failedToFetchData = result.isFailure,
                )
            }
        }
    }
}
