package com.paulohc.movlist.navigation

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun CustomNavigationBar(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val items = listOf(
        CustomNavigationBarItem.Home,
        CustomNavigationBarItem.Search,
    )

    val shouldShowBar = items.any { it.screen.routePrefix == currentDestination?.route }

    if (shouldShowBar) {
        NavigationBar(
            modifier = Modifier.heightIn(95.dp)
        ) {
            items.forEach { item ->
                val isSelected = currentDestination?.route == item.screen.routePrefix
                val title = stringResource(id = item.title)
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon
                            else item.unselectedIcon,
                            contentDescription = title,
                            modifier = Modifier.size(33.dp)
                        )
                    },
                    label = {
                        Text(
                            text = title,
                            style = MaterialTheme
                                .typography
                                .titleLarge.copy(fontSize = 20.sp)
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.screen.routePrefix) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
