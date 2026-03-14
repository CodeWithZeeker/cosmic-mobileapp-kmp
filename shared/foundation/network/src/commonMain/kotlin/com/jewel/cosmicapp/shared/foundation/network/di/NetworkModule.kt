package com.jewel.cosmicapp.shared.foundation.network.di

import com.jewel.cosmicapp.shared.foundation.network.HttpClientProvider
import com.jewel.cosmicapp.shared.foundation.network.config.BaseApiConfig
import com.jewel.cosmicapp.shared.foundation.network.getHttpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientEngine> { getHttpClientEngine() }
    
    single<BaseApiConfig> { 
        BaseApiConfig(
            baseUrl = "https://api.cosmicapp.com/",
            timeoutMillis = 30_000,
            isLoggingEnabled = true
        )
    }

    single { HttpClientProvider(get()) }

    single<HttpClient> { get<HttpClientProvider>().create() }
}
