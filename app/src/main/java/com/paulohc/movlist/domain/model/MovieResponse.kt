package com.paulohc.movlist.domain.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int,
)
