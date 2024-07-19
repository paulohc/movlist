package com.paulohc.movlist.presentation.screen.home

import com.paulohc.movlist.data.model.Movie

data class HomeUiState(
    val trendingMovies: List<Movie>? = null,
    val popularMovies: List<Movie>? = null,
    val topRatedMovies: List<Movie>? = null,
    val upcomingMovies: List<Movie>? = null,
    val failedToFetchData: Boolean = false,
)
