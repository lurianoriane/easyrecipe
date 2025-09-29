package presentation.intent

sealed interface SearchRecipeIntent {
    data class OnSearchRecipe(val nameRecipe: String) : SearchRecipeIntent
    data class OnFilterClick(val mealType: String) : SearchRecipeIntent
    data object OnFilterClean : SearchRecipeIntent
}