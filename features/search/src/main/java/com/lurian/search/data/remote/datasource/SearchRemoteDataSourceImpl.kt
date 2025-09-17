package com.lurian.search.data.remote.datasource

import com.lurian.search.data.remote.api.SearchApi
import com.lurian.search.data.remote.model.SearchRecipeResponse
import com.lurian.search.domain.model.Recipe

private const val LIMIT = 0

class SearchRemoteDataSourceImpl(
    private val searchApi: SearchApi
) : SearchRemoteDataSource {
    override suspend fun searchRecipes(nameRecipe: String): List<Recipe> {
        return searchApi.searchRecipe(LIMIT, nameRecipe).toRecipeList()
    }

    private fun SearchRecipeResponse.toRecipeList(): List<Recipe> {
        return recipes.map {
            Recipe(
                id = it.id.toString(),
                name = it.name,
                image = it.image,
                mealType = it.mealType
            )
        }
    }
}