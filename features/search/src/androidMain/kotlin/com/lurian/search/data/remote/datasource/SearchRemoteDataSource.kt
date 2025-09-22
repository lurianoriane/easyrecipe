package com.lurian.search.data.remote.datasource

import com.lurian.search.domain.model.Recipe

interface SearchRemoteDataSource {
    suspend fun searchRecipes(nameRecipe : String) : List<Recipe>
}