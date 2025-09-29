package domain.usecase

import domain.model.Recipe
import domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRecipeUseCase(
    private val searchRepository: SearchRepository
) {
    fun searchRecipes(nameRecipe: String): Flow<List<Recipe>> {
        return searchRepository.searchRecipes(nameRecipe)
    }
}