package com.lurian.search.presentation.intent

sealed interface SearchRecipeIntent {
    data class OnSearchRecipe(val nameRecipe: String) : SearchRecipeIntent
}