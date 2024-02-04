package com.paulohc.movlist.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable

fun NavController.navigate(screen: Screen) {
    this.navigate(route = screen.route)
}

fun NavGraphBuilder.composable(
    screen: Screen,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable(route = screen.route, arguments = screen.arguments, content = content)
}
