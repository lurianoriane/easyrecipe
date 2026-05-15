package com.lurian.design_system.components.bottomnavbar

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
import androidx.compose.ui.unit.dp
import com.lurian.design_system.components.theme.EasyRecipeTheme
import com.lurian.design_system.components.utils.DesignSystemDrawableRes
import com.lurian.designsystem.generated.resources.ic_menu_favorite_selected
import com.lurian.designsystem.generated.resources.ic_menu_favorite_unselected
import com.lurian.designsystem.generated.resources.ic_menu_home_selected
import com.lurian.designsystem.generated.resources.ic_menu_home_unselected
import com.lurian.designsystem.generated.resources.ic_menu_profile_selected
import com.lurian.designsystem.generated.resources.ic_menu_profile_unselected
import com.lurian.designsystem.generated.resources.ic_menu_search_selected
import com.lurian.designsystem.generated.resources.ic_menu_search_unselected
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavBar(
    selectedRoute: String,
    items: List<BottomNavItem>,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar {
        items.forEach { item ->
            NavBarItem(
                item = item,
                selected = selectedRoute == item.route,
                onClick = { onItemClick(item) }
            )
        }
    }
}

@Composable
private fun RowScope.NavBarItem(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val animatedSize by animateDpAsState(
        targetValue = if (selected) 30.dp else 25.dp,
        label = "",
        animationSpec = tween(400)
    )
    NavigationBarItem(
        interactionSource = remember { MutableInteractionSource() },
        selected = selected,
        onClick = onClick,
        icon = {
            Image(
                modifier = Modifier.size(animatedSize),
                painter = painterResource(if (selected) item.iconSelected else item.iconUnselected),
                contentDescription = item.title
            )
        },
        colors = NavigationBarItemDefaults.colors().copy(selectedIndicatorColor = Color.Transparent)
    )
}

private val previewBottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = "home",
        iconSelected = DesignSystemDrawableRes.ic_menu_home_selected,
        iconUnselected = DesignSystemDrawableRes.ic_menu_home_unselected,
    ),
    BottomNavItem(
        title = "Search",
        route = "search",
        iconSelected = DesignSystemDrawableRes.ic_menu_search_selected,
        iconUnselected = DesignSystemDrawableRes.ic_menu_search_unselected,
    ),
    BottomNavItem(
        title = "Favorites",
        route = "favorites",
        iconSelected = DesignSystemDrawableRes.ic_menu_favorite_selected,
        iconUnselected = DesignSystemDrawableRes.ic_menu_favorite_unselected,
    ),
    BottomNavItem(
        title = "Profile",
        route = "profile",
        iconSelected = DesignSystemDrawableRes.ic_menu_profile_selected,
        iconUnselected = DesignSystemDrawableRes.ic_menu_profile_unselected,
    ),
)

@Preview
@Composable
private fun BottomNavBarPreview() {
    var selectedRoute by remember { mutableStateOf(previewBottomNavItems.first().route) }

    EasyRecipeTheme {
        BottomNavBar(
            selectedRoute = selectedRoute,
            items = previewBottomNavItems,
            onItemClick = { item -> selectedRoute = item.route },
        )
    }
}

