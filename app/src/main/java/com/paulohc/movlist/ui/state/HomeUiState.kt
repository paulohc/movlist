package com.paulohc.movlist.ui.state

import com.paulohc.movlist.data.model.Movie
import javax.annotation.concurrent.Immutable

@Immutable
data class HomeUiState(
    val trendingMovies: List<Movie>? = null,
    val popularMovies: List<Movie>? = null,
    val topRatedMovies: List<Movie>? = null,
    val upcomingMovies: List<Movie>? = null,
    val failedToFetchData: Boolean = false,
)
