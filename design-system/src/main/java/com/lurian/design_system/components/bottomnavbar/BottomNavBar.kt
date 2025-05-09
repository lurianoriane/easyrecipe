package com.lurian.design_system.components.bottomnavbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lurian.designsystem.R

const val SHOULD_DISPLAY_BOTTOM_BAR = "should_display_bottom_bar"

@Composable
fun BottomNavBar(navController: NavController, items: List<BottomNavItem>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var showBottomNavBar by remember { mutableStateOf(true) }
    currentDestination?.arguments?.get(SHOULD_DISPLAY_BOTTOM_BAR)?.defaultValue.let {
        showBottomNavBar = it as? Boolean ?: true
    }
    AnimatedVisibility(showBottomNavBar) {
        NavigationBar {
            items.forEach { item ->
                NavBarItem(item = item, navController = navController)
            }
        }
    }

}

@Composable
private fun RowScope.NavBarItem(
    item: BottomNavItem,
    navController: NavController
) {
    val selected = navController.getCurrentRoute() == item.route
    val animatedSize by animateDpAsState(
        targetValue = if (selected) 30.dp else 25.dp,
        label = "",
        animationSpec = tween(400)
    )
    NavigationBarItem(
        interactionSource = remember { MutableInteractionSource() },
        selected = selected,
        onClick = navigate(item = item, navController = navController),
        icon = {
            Image(
                modifier = Modifier.size(animatedSize),
                painter = painterResource(id = if (selected) item.iconSelected else item.iconUnselected),
                contentDescription = item.title
            )
        },
        colors = NavigationBarItemDefaults.colors().copy(selectedIndicatorColor = Color.Transparent)
    )
}

@Composable
private fun navigate(item: BottomNavItem, navController: NavController): (() -> Unit) = {
    navController.navigate(item.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
private fun NavController.getCurrentRoute(): String? {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
private fun BottomNavBarPrev() {
    BottomNavBar(
        navController = NavController(context = LocalContext.current),
        items = listOf(
            BottomNavItem(
                title = "Home",
                iconSelected = R.drawable.ic_menu_favorite_selected,
                iconUnselected = R.drawable.ic_menu_home_unselected,
                route = "home"
            ),
            BottomNavItem(
                title = "Search",
                iconSelected = R.drawable.ic_menu_search_selected,
                iconUnselected = R.drawable.ic_menu_search_unselected,
                route = "search"
            ),
            BottomNavItem(
                title = "Profile",
                iconSelected = R.drawable.ic_menu_profile_selected,
                iconUnselected = R.drawable.ic_menu_profile_unselected,
                route = "profile"
            ),
            BottomNavItem(
                title = "Favorites",
                iconSelected = R.drawable.ic_menu_favorite_selected,
                iconUnselected = R.drawable.ic_menu_favorite_unselected,
                route = "settings"
            )
        )
    )
}