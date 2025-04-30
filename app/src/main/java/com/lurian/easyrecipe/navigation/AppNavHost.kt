package com.lurian.easyrecipe.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lurian.design_system.components.bottomnavbar.BottomNavItem
import com.lurian.design_system.components.utils.DesignSystemDrawableRes
import com.lurian.easyrecipe.presentation.HomePageScreenSuccess
import com.lurian.search.presentation.view.SearchRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppNavDestinations.HOME_ROUTE
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {

            composable(route = AppNavDestinations.HOME_ROUTE) {
                HomePageScreenSuccess()
            }

            composable(route = AppNavDestinations.SEARCH_ROUTE) {
                SearchRoute(hiltViewModel())
            }

            composable(route = AppNavDestinations.PROFILE_ROUTE) {
                HomePageScreenSuccess()
            }

            composable(route = AppNavDestinations.DETAILS_ROUTE) {
                HomePageScreenSuccess()
            }
        }
    }
}

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = AppNavDestinations.HOME_ROUTE,
        iconSelected = DesignSystemDrawableRes.fire_icon,
        iconUnselected = DesignSystemDrawableRes.fire_icon
    ),
    BottomNavItem(
        title = "Search",
        route = AppNavDestinations.SEARCH_ROUTE,
        iconSelected = DesignSystemDrawableRes.fire_icon,
        iconUnselected = DesignSystemDrawableRes.fire_icon
    ),
    BottomNavItem(
        title = "Profile",
        route = AppNavDestinations.PROFILE_ROUTE,
        iconSelected = DesignSystemDrawableRes.fire_icon,
        iconUnselected = DesignSystemDrawableRes.fire_icon
    ),
    BottomNavItem(
        title = "Details",
        route = AppNavDestinations.DETAILS_ROUTE,
        iconSelected = DesignSystemDrawableRes.fire_icon,
        iconUnselected = DesignSystemDrawableRes.fire_icon
    ),
)

internal object AppNavDestinations {
    const val HOME_ROUTE = "home"
    const val SEARCH_ROUTE = "search"
    const val PROFILE_ROUTE = "profile"
    const val DETAILS_ROUTE = "details"
}