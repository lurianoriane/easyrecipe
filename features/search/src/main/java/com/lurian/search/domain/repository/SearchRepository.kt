package com.lurian.search.domain.repository

import com.lurian.search.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchRecipes(nameRecipe: String): Flow<List<Recipe>>
}