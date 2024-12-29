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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
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
        CustomNavigationBarItem.About,
    )

    val shouldShowBar = items.any { item ->
        currentDestination?.hierarchy?.any {
            it.hasRoute(item.screen::class)
        } == true
    }

    if (shouldShowBar) {
        NavigationBar(
            modifier = Modifier.heightIn(95.dp)
        ) {
            items.forEach { item ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.hasRoute(item.screen::class)
                } == true
                val title = stringResource(id = item.title)
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon
                            else item.unselectedIcon,
                            contentDescription = title,
                            modifier = Modifier.size(33.dp),
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    },
                    label = {
                        Text(
                            text = title,
                            style = MaterialTheme
                                .typography
                                .titleLarge.copy(fontSize = 20.sp),
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.screen) {
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
