package com.example.search.presentation.intent

sealed interface SearchRecipeIntent {
    data class Search(val nameRecipe: String) : SearchRecipeIntent
}