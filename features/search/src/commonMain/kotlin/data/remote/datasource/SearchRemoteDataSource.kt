package data.remote.datasource

import domain.model.Recipe

interface SearchRemoteDataSource {
    suspend fun searchRecipes(nameRecipe : String) : List<Recipe>
}