package com.lurian.shared

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatformTools
import platform.UIKit.UIViewController

private const val baseUrl = "https://dummyjson.com/recipes/"

@Suppress("unused")
fun MainViewController(): UIViewController {
    if (KoinPlatformTools.defaultContext().getOrNull() == null) {
        startKoin {
            properties(mapOf("BASE_URL" to baseUrl))
            modules(SharedInjection.modules())
        }
    }

    return ComposeUIViewController {
        AppRootView()
    }
}
