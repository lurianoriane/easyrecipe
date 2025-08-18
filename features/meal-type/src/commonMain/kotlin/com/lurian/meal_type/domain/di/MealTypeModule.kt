package com.lurian.meal_type.domain.di

import com.lurian.meal_type.domain.usecase.GetMealTypesUseCase
import org.koin.dsl.module

val mealTypeModule = module {
    factory { GetMealTypesUseCase() }
}