package com.paulohc.movlist.presentation.screen.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paulohc.movlist.R
import com.paulohc.movlist.common.Constants
import com.paulohc.movlist.presentation.component.FallbackMessage

@Composable
fun DetailsScreen(
    state: DetailsUiState,
    navigateBack: () -> Unit,
) {
    val movieDetails = state.movieDetails
    val failedToFetchData = state.failedToFetchData
    val scrollState = rememberScrollState()

    if (failedToFetchData) {
        Box(modifier = Modifier.fillMaxSize()) {
            FallbackMessage(message = stringResource(id = R.string.check_internet_connection))
            DetailsTopBar(navigateBack)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(230.dp)
                        .fillMaxWidth()
                        .background(Color.Gray),
                    model = "${Constants.TMDB_BACKDROP_BASE_URL}${movieDetails?.backdropPath}",
                    contentDescription = movieDetails?.title,
                    contentScale = ContentScale.FillWidth,
                )
                DetailsTopBar(navigateBack)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 5.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movieDetails?.title ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = movieDetails?.overview ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    Text(
                        text = stringResource(id = R.string.release_date),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${movieDetails?.releaseDate}",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Text(
                        text = stringResource(id = R.string.rate),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${String.format("%.1f", movieDetails?.voteAverage)}/10",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(navigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { },
        colors = TopAppBarDefaults
            .centerAlignedTopAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.9f)),
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigate_back)
                )
            }
        }
    )
}
