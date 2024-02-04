package com.paulohc.movlist.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.paulohc.movlist.domain.MovieInfo
import com.paulohc.movlist.util.Constants
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteCarousel(
    movies: List<MovieInfo>,
    title: String? = null,
    onCardPress: ((Int) -> Unit)? = null,
) {
    val initial = remember {
        val init = Short.MAX_VALUE.toInt() / 2
        val res = init % movies.size
        init + res - 1
    }

    val pagerState = rememberPagerState(
        pageCount = { Short.MAX_VALUE.toInt() }, initialPage = initial
    )

    LaunchedEffect(pagerState) {
        var job = launch { autoScrollPager(pagerState) }
        pagerState.interactionSource.interactions.collectLatest {
            job.cancel()
            delay(5000)
            job = launch { autoScrollPager(pagerState) }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!title.isNullOrBlank()) {
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = title,
                color = Color.Black,
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 40.sp)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(200.dp),
            pageSpacing = 30.dp,
            contentPadding = PaddingValues(horizontal = 100.dp),
        ) { page ->
            val newPage = page % movies.size
            val movie = movies[newPage]
            AsyncImage(
                modifier = Modifier
                    .clickable {
                        onCardPress?.invoke(movie.id)
                    }
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )


                        this.scaleX = lerp(
                            start = 1f,
                            stop = 1.1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )

                        this.scaleY = lerp(
                            start = 1f,
                            stop = 1.1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .clip(shape = RoundedCornerShape(10.dp))
                    .height(300.dp)
                    .fillMaxWidth()
                    .background(Color.Gray),
                model = "${Constants.TMDB_POSTER_BASE_URL}${movie.posterPath}",
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
suspend fun autoScrollPager(pagerState: PagerState) {
    while (true) {
        delay(2000)

        withContext(NonCancellable) {
            if (pagerState.currentPage + 1 in 0..Short.MAX_VALUE) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            } else {
                val init = Short.MAX_VALUE.toInt() / 2
                init
            }
        }
    }
}
