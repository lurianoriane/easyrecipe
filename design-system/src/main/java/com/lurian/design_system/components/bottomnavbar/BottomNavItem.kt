package com.lurian.design_system.components.bottomnavbar

import androidx.annotation.DrawableRes

data class BottomNavItem(
    @DrawableRes val iconSelected: Int,
    @DrawableRes val iconUnselected: Int,
    val title: String,
    val route: String
)