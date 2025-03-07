package com.example.search.data.remote.api

import com.example.search.data.remote.model.SearchRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search")
    suspend fun searchRecipe(
        @Query("limit") limit: Int,
        @Query("q") query: String
    ) : SearchRecipeResponse
}