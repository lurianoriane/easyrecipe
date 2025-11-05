package com.lurian.desktop

import androidx.compose.ui.window.singleWindowApplication
import com.lurian.shared.AppRootView
import com.lurian.shared.SharedInjection
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(SharedInjection.modules())
    }
    singleWindowApplication(title = "EasyRecipe") {
        AppRootView()
    }
}