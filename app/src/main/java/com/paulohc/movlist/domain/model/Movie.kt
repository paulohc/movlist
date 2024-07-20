package com.paulohc.movlist.domain.model

data class Movie(
    val backdropPath: String?,
    val id: Int,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)
