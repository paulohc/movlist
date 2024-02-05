package com.paulohc.movlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.paulohc.movlist.navigation.*
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
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        modifier = Modifier.background(Color.Blue),
                        bottomBar = {
                            CustomNavigationBar(navController = navController)
                        }
                    ) { innerPadding ->
                        SetupNavGraph(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = Screen.Home,
                        )
                    }
                }
            }
        }
    }
}
