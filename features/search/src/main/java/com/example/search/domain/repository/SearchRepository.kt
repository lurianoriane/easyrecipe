package com.example.search.domain.repository

import com.example.search.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchRecipes(nameRecipe: String): Flow<List<Recipe>>
}