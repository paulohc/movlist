package com.paulohc.movlist.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.paulohc.movlist.common.Constants
import com.paulohc.movlist.domain.model.Movie

@Composable
fun Section(
    movies: List<Movie>,
    title: String? = null,
    onCardPress: ((Int) -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!title.isNullOrBlank()) {
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 40.sp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(movies) {
                AsyncImage(
                    modifier = Modifier
                        .clickable {
                            onCardPress?.invoke(it.id)
                        }
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
