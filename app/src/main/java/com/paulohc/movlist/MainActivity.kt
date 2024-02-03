package com.paulohc.movlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulohc.movlist.ui.screens.home.HomeScreen
import com.paulohc.movlist.ui.screens.home.HomeViewModel
import com.paulohc.movlist.ui.theme.MovlistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovlistTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: HomeViewModel = hiltViewModel()
                    val state = viewModel.state.collectAsState().value
                    HomeScreen(
                        state = state,
                        fetchMovies = viewModel::fetchMovies
                    )
                }
            }
        }
    }
}
