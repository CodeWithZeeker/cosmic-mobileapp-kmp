package com.jewel.cosmicapp.shared.foundation.network

import com.jewel.cosmicapp.shared.foundation.network.config.BaseApiConfig
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.HttpTimeout
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientProvider(
    private val config: BaseApiConfig
) {
    fun create(
        engineConfiguration: HttpClientConfig<*>.() -> Unit = {}
    ): HttpClient {
        return HttpClient(getHttpClientEngine()) {
            expectSuccess = true

            install(HttpTimeout) {
                requestTimeoutMillis = config.timeoutMillis
                connectTimeoutMillis = config.timeoutMillis
                socketTimeoutMillis = config.timeoutMillis
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }

            if (config.isLoggingEnabled) {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("Ktor: $message")
                        }
                    }
                }
            }

            defaultRequest {
                url(config.baseUrl)
                contentType(ContentType.Application.Json)
            }
            
            // This allows extending the client (e.g., adding Auth interceptors/plugins)
            engineConfiguration()
        }
    }
}
