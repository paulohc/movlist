package com.paulohc.movlist.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.paulohc.movlist.R
import com.paulohc.movlist.util.Constants

@Composable
fun SearchScreen(
    state: SearchScreenState,
    searchMovie: (String) -> Unit,
) {
    val searchedMovies = state.searchedMovies
    var text by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(text) {
        searchMovie(text)
    }

    Column {
        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(id = R.string.search)) },
                textStyle = TextStyle(fontSize = 20.sp),
            )
        }
        if (searchedMovies == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.no_results),
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
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
                            .height(200.dp)
                            .width(150.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(Color.Gray),
                        model = "${Constants.TMDB_IMAGE_BASE_URL}${it.posterPath}",
                        contentDescription = it.title,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}