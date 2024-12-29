package com.paulohc.movlist.navigation

import kotlinx.serialization.Serializable

const val ARG_MOVIE_ID = "arg_movie_id"

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object Search : Screen()

    @Serializable
    data class Details(
        val movieId: Int,
    ) : Screen()

    @Serializable
    data object About : Screen()
}
