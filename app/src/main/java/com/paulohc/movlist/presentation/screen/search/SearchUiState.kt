package com.paulohc.movlist.presentation.screen.search

import com.paulohc.movlist.data.model.Movie

data class SearchUiState(
    val searchedMovies: List<Movie>? = null,
    val failedToFetchData: Boolean = false,
)
