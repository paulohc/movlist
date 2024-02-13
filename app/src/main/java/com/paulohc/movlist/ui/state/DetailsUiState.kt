package com.paulohc.movlist.ui.state

import com.paulohc.movlist.data.model.Movie
import javax.annotation.concurrent.Immutable

@Immutable
data class DetailsUiState(
    val movieDetails: Movie? = null,
    val failedToFetchData: Boolean = false,
)
