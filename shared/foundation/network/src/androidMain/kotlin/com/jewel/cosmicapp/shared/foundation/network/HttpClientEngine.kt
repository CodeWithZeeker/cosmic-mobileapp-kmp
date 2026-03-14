package com.jewel.cosmicapp.shared.foundation.network // Must match commonMain

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun getHttpClientEngine(): HttpClientEngine = OkHttp.create()