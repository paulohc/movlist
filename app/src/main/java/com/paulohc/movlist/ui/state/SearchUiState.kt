package com.paulohc.movlist.ui.state

import com.paulohc.movlist.data.model.Movie
import javax.annotation.concurrent.Immutable

@Immutable
data class SearchUiState(
    val searchedMovies: List<Movie>? = null,
    val failedToFetchData: Boolean = false,
)
