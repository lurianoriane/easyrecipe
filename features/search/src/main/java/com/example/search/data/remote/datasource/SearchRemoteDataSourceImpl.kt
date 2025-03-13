package com.example.search.data.remote.datasource

import com.example.search.data.remote.api.SearchApi
import com.example.search.data.remote.model.SearchRecipeResponse
import com.example.search.domain.model.Recipe
import javax.inject.Inject

private const val LIMIT = 0

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRemoteDataSource {
    override suspend fun searchRecipes(nameRecipe: String): List<Recipe> {
       return searchApi.searchRecipe(LIMIT, nameRecipe).toRecipeList()
    }

    private fun SearchRecipeResponse.toRecipeList() : List<Recipe> {
        return recipes.map {
            Recipe(
                id = it.id.toString(),
                name = it.name,
                image = it.image
            )
        }
    }
}