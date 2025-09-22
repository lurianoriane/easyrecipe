package com.lurian.search.data.remote.api

import com.lurian.search.data.remote.model.SearchRecipeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class SearchApi(private val httpClient: HttpClient) {

    suspend fun searchRecipe(limit: Int, query: String): SearchRecipeResponse {
        return httpClient.get(
            "search"
        ) {
            parameter("limit", limit)
            parameter("query", query)
        }.body()
    }
}