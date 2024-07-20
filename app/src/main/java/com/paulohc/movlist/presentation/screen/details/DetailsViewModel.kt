package com.paulohc.movlist.presentation.screen.details

import androidx.lifecycle.*
import androidx.navigation.toRoute
import com.paulohc.movlist.domain.repository.MovieRepository
import com.paulohc.movlist.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsUiState())
    val state = _state.asStateFlow()

    private val movieId: Int = savedStateHandle.toRoute<Screen.Details>().movieId

    init {
        fetchMovieDetails()
    }

    private fun fetchMovieDetails() {
        viewModelScope.launch {
            val result = movieRepository.getMovieDetails(movieId)
            _state.update {
                it.copy(
                    movieDetails = result.getOrNull(),
                    failedToFetchData = result.isFailure,
                )
            }
        }
    }
}
