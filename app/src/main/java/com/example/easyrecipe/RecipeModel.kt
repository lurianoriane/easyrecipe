package com.example.easyrecipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

data class RecipeModel(
    val name: String,
    val image: Int
)

@Composable
fun retrieveRecipeList(): SnapshotStateList<RecipeModel> {
    val recipeList = remember {
        mutableStateListOf(
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.ic_launcher_background),
        )
    }
    return recipeList

}