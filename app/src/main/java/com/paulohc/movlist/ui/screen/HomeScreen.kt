package com.paulohc.movlist.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.paulohc.movlist.R
import com.paulohc.movlist.ui.component.*
import com.paulohc.movlist.ui.state.HomeUiState

@Composable
fun HomeScreen(
    state: HomeUiState,
    fetchMovies: () -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    val trendingMovies = state.trendingMovies
    val popularMovies = state.popularMovies
    val topRatedMovies = state.topRatedMovies
    val upcomingMovies = state.upcomingMovies
    val failedToFetchData = state.failedToFetchData

    LaunchedEffect(Unit) {
        fetchMovies()
    }

    if (
        failedToFetchData &&
        trendingMovies == null &&
        popularMovies == null &&
        topRatedMovies == null &&
        upcomingMovies == null
    ) {
        FallbackMessage(message = stringResource(id = R.string.check_internet_connection))
    } else {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 40.dp)
        ) {
            item {
                trendingMovies?.let {
                    InfiniteCarousel(
                        movies = it,
                        title = stringResource(id = R.string.trending_movies),
                        onCardPress = navigateToDetails,
                    )
                }
            }
            item {
                popularMovies?.let {
                    Spacer(modifier = Modifier.height(50.dp))
                    Section(
                        movies = it,
                        title = stringResource(id = R.string.popular_movies),
                        onCardPress = navigateToDetails,
                    )
                }
            }
            item {
                topRatedMovies?.let {
                    Spacer(modifier = Modifier.height(50.dp))
                    Section(
                        movies = it,
                        title = stringResource(id = R.string.top_rated_movies),
                        onCardPress = navigateToDetails,
                    )
                }
            }
            item {
                upcomingMovies?.let {
                    Spacer(modifier = Modifier.height(50.dp))
                    Section(
                        movies = it,
                        title = stringResource(id = R.string.upcoming_movies),
                        onCardPress = navigateToDetails,
                    )
                }
            }
        }
    }
}
