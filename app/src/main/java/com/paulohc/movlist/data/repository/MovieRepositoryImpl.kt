package com.paulohc.movlist.data.repository

import com.paulohc.movlist.data.model.Movie
import com.paulohc.movlist.data.model.MovieResponse
import com.paulohc.movlist.data.remote.MovieApi
import com.paulohc.movlist.data.remote.dto.toMovie
import com.paulohc.movlist.data.remote.dto.toMovieResponse

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
) : MovieRepository {
    override suspend fun getMovieDetails(movieId: Int, language: String): Result<Movie> {
        val result = movieApi.getMovieDetails(
            movieId = movieId,
            language = language,
        )
        return result.map { it.toMovie() }
    }

    override suspend fun searchMovies(
        query: String,
        page: Int,
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.searchMovies(
            query = query,
            page = page,
            language = language,
        )
        return result.map { it.toMovieResponse() }
    }

    override suspend fun getTrendingMovies(
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.getTrendingMovies(language = language)
        return result.map { it.toMovieResponse() }
    }

    override suspend fun getPopularMovies(
        page: Int,
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.getPopularMovies(
            page = page,
            language = language,
        )
        return result.map { it.toMovieResponse() }
    }

    override suspend fun getTopRatedMovies(
        page: Int,
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.getTopRatedMovies(
            page = page,
            language = language,
        )
        return result.map { it.toMovieResponse() }
    }

    override suspend fun getUpcomingMovies(
        page: Int,
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.getUpcomingMovies(
            page = page,
            language = language,
        )
        return result.map { it.toMovieResponse() }
    }
}
