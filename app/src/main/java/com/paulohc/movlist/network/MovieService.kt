package com.paulohc.movlist.network

import com.paulohc.movlist.BuildConfig
import com.paulohc.movlist.domain.MovieSearchResult
import retrofit2.http.*

interface MovieService {
    @Headers("Authorization: Bearer ${BuildConfig.TMDB_API_TOKEN}")
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("language") language: String = "en-US",
    ): Result<MovieSearchResult>

    @Headers("Authorization: Bearer ${BuildConfig.TMDB_API_TOKEN}")
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US",
    ): Result<MovieSearchResult>

    @Headers("Authorization: Bearer ${BuildConfig.TMDB_API_TOKEN}")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US",
    ): Result<MovieSearchResult>

    @Headers("Authorization: Bearer ${BuildConfig.TMDB_API_TOKEN}")
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US",
    ): Result<MovieSearchResult>
}
