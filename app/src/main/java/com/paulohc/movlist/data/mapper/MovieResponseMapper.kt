package com.paulohc.movlist.data.mapper

import com.paulohc.movlist.data.remote.dto.MovieResponseDto
import com.paulohc.movlist.domain.model.MovieResponse

fun MovieResponseDto.toMovieResponse(): MovieResponse {
    return MovieResponse(
        page = page,
        results = results.toMovies(),
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
