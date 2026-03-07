package com.jewel.foundation.network.provider

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun getHttpClientEngine(): HttpClientEngine = OkHttp.create()
