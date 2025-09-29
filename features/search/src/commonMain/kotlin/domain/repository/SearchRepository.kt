package domain.repository

import domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchRecipes(nameRecipe: String): Flow<List<Recipe>>
}