package com.lurian.design_system.components.card.squarecard.model

data class SquareCardUiModel(
    val recipeName: String,
    val recipeImage: String,
    val recipeKcal: String,
    val recipeTime: String,
    val isFavorite: Boolean = false,
)
