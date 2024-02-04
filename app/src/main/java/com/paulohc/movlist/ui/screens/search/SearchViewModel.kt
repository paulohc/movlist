package com.paulohc.movlist.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulohc.movlist.domain.MovieInfo
import com.paulohc.movlist.network.MovieService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class SearchScreenState(
    val searchedMovies: List<MovieInfo>? = null,
)

private const val DEBOUNCE_TIME_MILLIS = 500L

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val service: MovieService,
) : ViewModel() {
    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()
    private var queryJob: Job? = null

    fun searchMovie(query: String) {
        queryJob?.cancel()

        if (query.isBlank()) {
            _state.update { it.copy(searchedMovies = null) }
            return
        }

        queryJob = viewModelScope.launch {
            delay(DEBOUNCE_TIME_MILLIS)
            val result = service.searchMovie(query)
            _state.update {
                it.copy(searchedMovies = result.getOrNull()?.results)
            }
        }
    }
}