package data.repository

import data.remote.datasource.SearchRemoteDataSource
import domain.model.Recipe
import domain.repository.SearchRepository
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