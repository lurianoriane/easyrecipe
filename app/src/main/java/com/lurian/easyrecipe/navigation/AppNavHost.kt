package com.lurian.easyrecipe.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lurian.design_system.components.bottomnavbar.BottomNavItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    startDestination: String = AppNavDestinations.HOME_ROUTE
) {

    SharedTransitionLayout {

    }

}

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = AppNavDestinations.HOME_ROUTE,
        iconSelected = TODO(),
        iconUnselected = TODO()
    ),
    BottomNavItem(
        title = "Search",
        route = AppNavDestinations.SEARCH_ROUTE,
        iconSelected = TODO(),
        iconUnselected = TODO()
    )
)

internal object AppNavDestinations {
    const val HOME_ROUTE = "home"
    const val SEARCH_ROUTE = "search"
}