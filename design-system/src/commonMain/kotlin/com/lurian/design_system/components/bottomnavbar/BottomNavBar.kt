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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

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
