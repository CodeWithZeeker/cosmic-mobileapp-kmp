package com.jewel.foundation.network.foundation.network.di

import org.koin.dsl.module

val networkModule = module {
    single { createHttpClientEngine() }
    single { createHttpClient(get()) }
}
