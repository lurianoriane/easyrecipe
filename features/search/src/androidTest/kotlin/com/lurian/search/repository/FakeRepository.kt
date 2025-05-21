package com.lurian.search.repository

import com.lurian.search.domain.model.Recipe
import com.lurian.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeRepository @Inject constructor() : SearchRepository {
    override fun searchRecipes(nameRecipe: String): Flow<List<Recipe>> {
        return flowOf(listOf())
    }
}