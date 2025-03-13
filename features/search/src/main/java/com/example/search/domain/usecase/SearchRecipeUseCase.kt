package com.example.search.domain.usecase

import com.example.search.domain.model.Recipe
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    fun searchRecipes(nameRecipe: String): Flow<List<Recipe>> {
        return searchRepository.searchRecipes(nameRecipe)
    }
}