package com.lurian.shared

import com.lurian.meal_type.domain.di.mealTypeModule
import org.koin.core.module.Module

object SharedInjection {
    fun modules(): List<Module> {
        return listOf(
            mealTypeModule
        )
    }
}