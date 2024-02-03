package com.paulohc.movlist.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.paulohc.movlist.R
import com.paulohc.movlist.ui.components.InfiniteCarousel
import com.paulohc.movlist.ui.components.Section

@Composable
fun HomeScreen(
    state: HomeState,
    fetchMovies: () -> Unit,
) {
    LaunchedEffect(Unit) {
        fetchMovies()
    }
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 40.dp)
        ) {
            item {
                state.trendingMovies?.let {
                    InfiniteCarousel(
                        movies = it,
                        stringResource(id = R.string.trending_movies)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
                state.popularMovies?.let {
                    Section(
                        movies = it,
                        title = stringResource(id = R.string.popular_movies)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
                state.topRatedMovies?.let {
                    Section(
                        movies = it,
                        title = stringResource(id = R.string.top_rated_movies)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
                state.upcomingMovies?.let {
                    Section(
                        movies = it,
                        title = stringResource(id = R.string.upcoming_movies)
                    )
                }
            }
        }
    }
}
