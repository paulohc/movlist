package com.paulohc.movlist.navigation

import androidx.navigation.*

const val ARG_MOVIE_ID = "arg_movie_id"

sealed class Screen(
    routePrefix: String,
    arguments: List<NamedNavArgument> = emptyList(),
) : BaseScreen(routePrefix = routePrefix, arguments = arguments) {
    data object Home : Screen(routePrefix = "home_screen")
    data object Search : Screen(routePrefix = "search_screen")

    data object Details : Screen(
        routePrefix = "details_screen",
        arguments = listOf(
            navArgument(name = ARG_MOVIE_ID) {
                type = NavType.IntType
                nullable = false
            },
        ),
    ) {
        fun withArgs(movieId: Int): String {
            return buildRouteWithParams(mapOf(ARG_MOVIE_ID to movieId))
        }
    }
}
