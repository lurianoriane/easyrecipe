package com.lurian.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lurian.design_system.components.bottomnavbar.BottomNavBar
import com.lurian.design_system.components.theme.EasyRecipeTheme
import com.lurian.shared.navigation.AppNavHost
import com.lurian.shared.navigation.bottomNavItems

@Composable
fun AppRootView() {
    EasyRecipeTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    items = bottomNavItems
                )
            },
        ) { innerPadding ->
            AppNavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}