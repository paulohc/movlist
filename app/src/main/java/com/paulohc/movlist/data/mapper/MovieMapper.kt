package com.paulohc.movlist.data.mapper

import com.paulohc.movlist.data.remote.dto.MovieDto
import com.paulohc.movlist.domain.model.Movie

fun MovieDto.toMovie(): Movie {
    return Movie(
        backdropPath = backdropPath,
        id = id,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
    )
}

fun List<MovieDto>.toMovies(): List<Movie> = this.map(MovieDto::toMovie)
