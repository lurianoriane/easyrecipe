package com.lurian.easyrecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.lurian.easyrecipe.ui.theme.EasyRecipeTheme
import com.lurian.search.presentation.view.SearchRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasyRecipeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    SearchRoute()
                }
            }
        }
    }
}