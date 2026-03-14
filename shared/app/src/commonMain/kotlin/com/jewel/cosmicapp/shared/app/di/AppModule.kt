package com.jewel.cosmicapp.shared.app.di

import com.jewel.cosmicapp.shared.features.auth.di.authModule
import com.jewel.cosmicapp.shared.foundation.network.di.networkModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    includes(networkModule, authModule)
}
