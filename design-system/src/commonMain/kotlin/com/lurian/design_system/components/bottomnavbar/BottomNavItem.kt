package com.lurian.design_system.components.bottomnavbar

import org.jetbrains.compose.resources.DrawableResource

data class BottomNavItem(
    val iconSelected: DrawableResource,
    val iconUnselected: DrawableResource,
    val title: String,
    val route: String
)