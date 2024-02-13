package com.paulohc.movlist.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.paulohc.movlist.data.model.MovieResponse

data class MovieResponseDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)

fun MovieResponseDto.toMovieResponse(): MovieResponse {
    return MovieResponse(
        page = page,
        results = results.map { it.toMovie() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
