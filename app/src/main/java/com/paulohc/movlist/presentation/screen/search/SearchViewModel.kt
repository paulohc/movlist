package com.paulohc.movlist.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulohc.movlist.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val DEBOUNCE_TIME_MILLIS = 500L

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()
    private var queryJob: Job? = null

    fun searchMovies(query: String) {
        queryJob?.cancel()

        if (query.isBlank()) {
            _state.update {
                it.copy(
                    searchedMovies = null,
                    failedToFetchData = false
                )
            }
            return
        }

        queryJob = viewModelScope.launch {
            delay(DEBOUNCE_TIME_MILLIS)
            val result = movieRepository.searchMovies(query)
            _state.update {
                it.copy(
                    searchedMovies = result.getOrNull()?.results,
                    failedToFetchData = result.isFailure,
                )
            }
        }
    }
}
