package com.lurian.shared.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lurian.design_system.components.bottomnavbar.BottomNavItem
import com.lurian.design_system.components.utils.DesignSystemDrawableRes
import com.lurian.designsystem.generated.resources.ic_menu_favorite_selected
import com.lurian.designsystem.generated.resources.ic_menu_favorite_unselected
import com.lurian.designsystem.generated.resources.ic_menu_home_selected
import com.lurian.designsystem.generated.resources.ic_menu_home_unselected
import com.lurian.designsystem.generated.resources.ic_menu_profile_selected
import com.lurian.designsystem.generated.resources.ic_menu_profile_unselected
import com.lurian.designsystem.generated.resources.ic_menu_search_selected
import com.lurian.designsystem.generated.resources.ic_menu_search_unselected
import presentation.view.SearchRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    selectedRoute: String = AppNavDestinations.HOME_ROUTE
) {
    SharedTransitionLayout {
        when (selectedRoute) {
            AppNavDestinations.HOME_ROUTE,
            AppNavDestinations.SEARCH_ROUTE,
            AppNavDestinations.PROFILE_ROUTE,
            AppNavDestinations.FAVORITE_ROUTE -> SearchRoute(modifier = modifier)

            else -> SearchRoute(modifier = modifier)
        }
    }
}

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = AppNavDestinations.HOME_ROUTE,
        iconSelected = DesignSystemDrawableRes.ic_menu_home_selected,
        iconUnselected = DesignSystemDrawableRes.ic_menu_home_unselected
    ),
    BottomNavItem(
        title = "Search",
        route = AppNavDestinations.SEARCH_ROUTE,
        iconSelected = DesignSystemDrawableRes.ic_menu_search_selected,
        iconUnselected = DesignSystemDrawableRes.ic_menu_search_unselected
    ),
    BottomNavItem(
        title = "Favorite",
        route = AppNavDestinations.FAVORITE_ROUTE,
        iconSelected = DesignSystemDrawableRes.ic_menu_favorite_selected,
        iconUnselected = DesignSystemDrawableRes.ic_menu_favorite_unselected
    ),
    BottomNavItem(
        title = "Profile",
        route = AppNavDestinations.PROFILE_ROUTE,
        iconSelected = DesignSystemDrawableRes.ic_menu_profile_selected,
        iconUnselected = DesignSystemDrawableRes.ic_menu_profile_unselected
    ),
)

internal object AppNavDestinations {
    const val HOME_ROUTE = "home"
    const val SEARCH_ROUTE = "search"
    const val PROFILE_ROUTE = "profile"
    const val FAVORITE_ROUTE = "favorites"
}
