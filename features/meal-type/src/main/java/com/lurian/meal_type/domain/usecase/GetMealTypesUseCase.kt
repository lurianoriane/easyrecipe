package com.lurian.meal_type.domain.usecase

import com.lurian.meal_type.domain.model.MealType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealTypesUseCase @Inject constructor() {
    fun getMealTypes(): Flow<List<String>> {
        return flow {
            emit(MealType.entries.map {
                it.title
            })
        }
    }
}