package com.paulohc.movlist.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.paulohc.movlist.ui.screen.*
import com.paulohc.movlist.ui.viewmodel.*

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.routePrefix,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        composable(Screen.Home) {
            val viewModel: HomeViewModel = hiltViewModel()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            HomeScreen(
                state = state,
                fetchMovies = viewModel::fetchMovies,
                navigateToDetails = {
                    navController.navigate(
                        Screen.Details.withArgs(movieId = it)
                    )
                }
            )
        }
        composable(Screen.Search) {
            val viewModel: SearchViewModel = hiltViewModel()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            SearchScreen(
                state = state,
                searchMovies = viewModel::searchMovies,
                navigateToDetails = {
                    navController.navigate(
                        Screen.Details.withArgs(movieId = it)
                    )
                }
            )
        }
        composable(Screen.Details) {
            val viewModel: DetailsViewModel = hiltViewModel()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            DetailsScreen(
                state = state,
                navigateBack = navController::popBackStack
            )
        }
    }
}
