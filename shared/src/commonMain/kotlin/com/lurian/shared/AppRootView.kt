package com.lurian.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.lurian.design_system.components.bottomnavbar.BottomNavBar
import com.lurian.design_system.components.theme.EasyRecipeTheme
import com.lurian.shared.navigation.AppNavHost
import com.lurian.shared.navigation.bottomNavItems

@Composable
fun AppRootView() {
    EasyRecipeTheme {
        var selectedRoute by rememberSaveable { mutableStateOf(bottomNavItems.first().route) }

        Scaffold(
            bottomBar = {
                BottomNavBar(
                    selectedRoute = selectedRoute,
                    items = bottomNavItems,
                    onItemClick = { item -> selectedRoute = item.route }
                )
            },
        ) { innerPadding ->
            AppNavHost(
                modifier = Modifier.padding(innerPadding),
                selectedRoute = selectedRoute
            )
        }
    }
}
