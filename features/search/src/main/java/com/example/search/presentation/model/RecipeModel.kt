package com.example.search.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.lurian.search.R

data class RecipeModel(
    val name: String,
    val image: Int
)

@Composable
fun retrieveRecipeList(): SnapshotStateList<RecipeModel> {
    val recipeList = remember {
        mutableStateListOf(
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
            RecipeModel(name = "Bolo de cenoura", image = R.drawable.hamburguer),
        )
    }
    return recipeList

}