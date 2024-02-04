package com.paulohc.movlist.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(
    routePrefix: String,
    arguments: List<NamedNavArgument> = emptyList(),
) : BaseScreen(routePrefix = routePrefix, arguments = arguments) {
    object Home : Screen(routePrefix = "home_screen")
    object Search : Screen(routePrefix = "search_screen")
}
