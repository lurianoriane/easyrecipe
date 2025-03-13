package com.example.search.data.remote.datasource

import com.example.search.domain.model.Recipe

interface SearchRemoteDataSource {
    suspend fun searchRecipes(nameRecipe : String) : List<Recipe>
}