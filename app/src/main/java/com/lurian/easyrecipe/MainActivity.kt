package com.lurian.easyrecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lurian.design_system.components.bottomnavbar.BottomNavBar
import com.lurian.easyrecipe.navigation.AppNavHost
import com.lurian.easyrecipe.navigation.bottomNavItems
import com.lurian.easyrecipe.ui.theme.EasyRecipeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
    }
}