package com.paulohc.movlist.data.repository

import com.paulohc.movlist.data.mapper.toMovie
import com.paulohc.movlist.data.mapper.toMovieResponse
import com.paulohc.movlist.data.remote.MovieApi
import com.paulohc.movlist.data.remote.dto.MovieDto
import com.paulohc.movlist.data.remote.dto.MovieResponseDto
import com.paulohc.movlist.domain.model.Movie
import com.paulohc.movlist.domain.model.MovieResponse
import com.paulohc.movlist.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
) : MovieRepository {
    override suspend fun getMovieDetails(movieId: Int, language: String): Result<Movie> {
        val result = movieApi.getMovieDetails(
            movieId = movieId,
            language = language,
        )
        return result.map(MovieDto::toMovie)
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
        return result.map(MovieResponseDto::toMovieResponse)
    }

    override suspend fun getTrendingMovies(
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.getTrendingMovies(language = language)
        return result.map(MovieResponseDto::toMovieResponse)
    }

    override suspend fun getPopularMovies(
        page: Int,
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.getPopularMovies(
            page = page,
            language = language,
        )
        return result.map(MovieResponseDto::toMovieResponse)
    }

    override suspend fun getTopRatedMovies(
        page: Int,
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.getTopRatedMovies(
            page = page,
            language = language,
        )
        return result.map(MovieResponseDto::toMovieResponse)
    }

    override suspend fun getUpcomingMovies(
        page: Int,
        language: String,
    ): Result<MovieResponse> {
        val result = movieApi.getUpcomingMovies(
            page = page,
            language = language,
        )
        return result.map(MovieResponseDto::toMovieResponse)
    }
}
