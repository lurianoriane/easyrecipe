package com.lurian.search.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRecipeResponse(
    @SerialName("recipes") val recipes: List<RecipeResponse>
)

@Serializable
data class RecipeResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String
)

