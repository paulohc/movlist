package com.paulohc.movlist.presentation.screen.details

import com.paulohc.movlist.domain.model.Movie

data class DetailsUiState(
    val movieDetails: Movie? = null,
    val failedToFetchData: Boolean = false,
)
