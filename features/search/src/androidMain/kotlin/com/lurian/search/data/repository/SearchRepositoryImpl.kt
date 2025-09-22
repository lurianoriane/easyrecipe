package com.lurian.search.data.repository

import com.lurian.search.data.remote.datasource.SearchRemoteDataSource
import com.lurian.search.domain.model.Recipe
import com.lurian.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val dataSource: SearchRemoteDataSource
) : SearchRepository {
    override fun searchRecipes(nameRecipe: String): Flow<List<Recipe>> {
        return flow {
            emit(dataSource.searchRecipes(nameRecipe))
        }
    }
}