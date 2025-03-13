package com.example.search.data.repository

import com.example.search.data.remote.datasource.SearchRemoteDataSource
import com.example.search.domain.model.Recipe
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: SearchRemoteDataSource
) : SearchRepository {
    override fun searchRecipes(nameRecipe: String): Flow<List<Recipe>> {
        return flow {
            emit(dataSource.searchRecipes(nameRecipe))
        }
    }
}