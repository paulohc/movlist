package com.paulohc.movlist.domain

import com.google.gson.annotations.SerializedName

data class MovieSearchResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieInfo>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)
