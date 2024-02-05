package com.paulohc.movlist.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulohc.movlist.domain.MovieInfo
import com.paulohc.movlist.network.MovieService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val trendingMovies: List<MovieInfo>? = null,
    val popularMovies: List<MovieInfo>? = null,
    val topRatedMovies: List<MovieInfo>? = null,
    val upcomingMovies: List<MovieInfo>? = null,
    val failedToFetchData: Boolean = false,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: MovieService,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun fetchMovies() {
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchTopRatedMovies()
        fetchUpcomingMovies()
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            val result = service.getTrendingMovies()
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
            val result = service.getPopularMovies()
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
            val result = service.getTopRatedMovies()
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
            val result = service.getUpcomingMovies()
            _state.update {
                it.copy(
                    upcomingMovies = result.getOrNull()?.results,
                    failedToFetchData = result.isFailure,
                )
            }
        }
    }
}
