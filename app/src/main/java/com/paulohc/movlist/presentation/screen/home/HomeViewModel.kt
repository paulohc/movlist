package com.paulohc.movlist.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulohc.movlist.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    fun fetchMovies() {
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchTopRatedMovies()
        fetchUpcomingMovies()
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            val result = movieRepository.getTrendingMovies()
            _state.update {
                it.copy(
                    trendingMovies = result.getOrNull()?.results,
                    failedToFetchData = result.isFailure,
                )
            }
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            val result = movieRepository.getPopularMovies()
            _state.update {
                it.copy(
                    popularMovies = result.getOrNull()?.results,
                    failedToFetchData = result.isFailure,
                )
            }
        }
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            val result = movieRepository.getTopRatedMovies()
            _state.update {
                it.copy(
                    topRatedMovies = result.getOrNull()?.results,
                    failedToFetchData = result.isFailure,
                )
            }
        }
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            val result = movieRepository.getUpcomingMovies()
            _state.update {
                it.copy(
                    upcomingMovies = result.getOrNull()?.results,
                    failedToFetchData = result.isFailure,
                )
            }
        }
    }
}
