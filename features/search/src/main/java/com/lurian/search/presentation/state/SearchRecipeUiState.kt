package com.lurian.search.presentation.state

import com.lurian.search.domain.model.Recipe

data class SearchRecipeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val listSearchRecipe: List<Recipe> = emptyList(),
    val listMealType: List<String> = emptyList()
) {
    fun initialState() = this.copy(
        isLoading = false,
        isError = false,
        listSearchRecipe = emptyList(),
        listMealType = emptyList()
    )
}