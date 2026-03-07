package com.jewel.cosmicapp.shared.foundation.network.config

data class BaseApiConfig(
    val baseUrl: String,
    val timeoutMillis: Long = 15000L,
    val isLoggingEnabled: Boolean = false
)
