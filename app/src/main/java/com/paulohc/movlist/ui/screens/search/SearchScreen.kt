package com.paulohc.movlist.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paulohc.movlist.R
import com.paulohc.movlist.ui.components.FallbackMessage
import com.paulohc.movlist.util.Constants

@Composable
fun SearchScreen(
    state: SearchScreenState,
    searchMovies: (String) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    val searchedMovies = state.searchedMovies
    val failedToFetchData = state.failedToFetchData
    var text by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(text) {
        searchMovies(text)
    }

    Column {
        Column(
            modifier = Modifier
                .heightIn(100.dp)
                .fillMaxWidth(),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                value = text,
                onValueChange = { text = it },
                textStyle = MaterialTheme.typography.headlineMedium,
                label = {
                    Text(
                        text = stringResource(id = R.string.search),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
        if (searchedMovies == null) {
            FallbackMessage(
                message = stringResource(
                    id = if (failedToFetchData) R.string.check_internet_connection
                    else R.string.no_results,
                )
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(searchedMovies) {
                    AsyncImage(
                        modifier = Modifier
                            .clickable { navigateToDetails(it.id) }
                            .height(200.dp)
                            .width(150.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(Color.Gray),
                        model = "${Constants.TMDB_POSTER_BASE_URL}${it.posterPath}",
                        contentDescription = it.title,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}
