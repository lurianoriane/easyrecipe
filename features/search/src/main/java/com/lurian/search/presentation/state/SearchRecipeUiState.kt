package com.lurian.search.presentation.state

import com.lurian.search.domain.model.Recipe

data class SearchRecipeUiState (
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val searchRecipe : List<Recipe> = emptyList()
){
    fun initialState() = this.copy(false, false, emptyList())
}