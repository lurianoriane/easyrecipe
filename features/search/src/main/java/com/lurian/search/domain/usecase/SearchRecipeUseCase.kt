package com.lurian.search.domain.usecase

import com.lurian.search.domain.model.Recipe
import com.lurian.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRecipeUseCase(
    private val searchRepository: SearchRepository
) {
    fun searchRecipes(nameRecipe: String): Flow<List<Recipe>> {
        return searchRepository.searchRecipes(nameRecipe)
    }
}