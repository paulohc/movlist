package com.paulohc.movlist.presentation.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulohc.movlist.R

@Composable
fun AboutScreen(
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Movlist",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayMedium.copy(fontSize = 40.sp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    .padding(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tmdb_logo),
                    contentDescription = "",
                    Modifier
                        .padding(end = 30.dp)
                        .size(50.dp)
                )
                Text(
                    text = "This application uses TMDB and the TMDB APIs but is not endorsed, certified, or otherwise approved by TMDB.",
                    style = MaterialTheme
                        .typography
                        .titleLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}