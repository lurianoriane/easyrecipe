package com.lurian.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkProviderImpl(
    private val baseUrl: String
) : NetworkProvider {
    override fun providesJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            explicitNulls = false
            encodeDefaults = true
        }
    }

    override fun providesHttpClientEngine(): HttpClientEngine {
        return OkHttp.create()
    }

    override fun providesKtorClient(engine: HttpClientEngine, json: Json): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(json)
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(baseUrl)
            }
        }
    }
}

