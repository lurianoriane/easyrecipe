package com.lurian.easyrecipe

import android.app.Application
import com.lurian.easyrecipe.di.AppInjection
import org.koin.core.context.startKoin

class RecipesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeDI()
    }

    private fun initializeDI() {
        startKoin {
            modules(AppInjection.modules())
            properties(
                mapOf(
                    "BASE_URL" to BuildConfig.BASE_URL
                )
            )
        }
    }
}