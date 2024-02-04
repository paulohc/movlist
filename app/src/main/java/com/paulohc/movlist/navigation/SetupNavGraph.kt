package com.paulohc.movlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.paulohc.movlist.ui.screens.home.HomeScreen
import com.paulohc.movlist.ui.screens.home.HomeViewModel
import com.paulohc.movlist.ui.screens.search.SearchScreen
import com.paulohc.movlist.ui.screens.search.SearchViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.routePrefix,
        modifier = modifier,
    ) {
        composable(Screen.Home) {
            val viewModel: HomeViewModel = hiltViewModel()
            val state = viewModel.state.collectAsState().value
            HomeScreen(
                state = state,
                fetchMovies = viewModel::fetchMovies
            )
        }
        composable(Screen.Search) {
            val viewModel: SearchViewModel = hiltViewModel()
            val state = viewModel.state.collectAsState().value
            SearchScreen(
                state = state,
                searchMovie = viewModel::searchMovie,
            )
        }
    }
}
