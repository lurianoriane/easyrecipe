package com.lurian.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import kotlinx.serialization.json.Json

interface NetworkProvider {
    fun providesJson(): Json
    fun providesHttpClientEngine(): HttpClientEngine
    fun providesKtorClient(engine: HttpClientEngine, json: Json): HttpClient
}

