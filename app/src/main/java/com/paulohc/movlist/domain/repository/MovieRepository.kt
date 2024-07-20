package com.paulohc.movlist.domain.repository

import com.paulohc.movlist.domain.model.Movie
import com.paulohc.movlist.domain.model.MovieResponse


interface MovieRepository {
    suspend fun getMovieDetails(
        movieId: Int,
        language: String = "en-US",
    ): Result<Movie>

    suspend fun searchMovies(
        query: String,
        page: Int = 1,
        language: String = "en-US",
    ): Result<MovieResponse>

    suspend fun getTrendingMovies(
        language: String = "en-US",
    ): Result<MovieResponse>

    suspend fun getPopularMovies(
        page: Int = 1,
        language: String = "en-US",
    ): Result<MovieResponse>

    suspend fun getTopRatedMovies(
        page: Int = 1,
        language: String = "en-US",
    ): Result<MovieResponse>

    suspend fun getUpcomingMovies(
        page: Int = 1,
        language: String = "en-US",
    ): Result<MovieResponse>
}
