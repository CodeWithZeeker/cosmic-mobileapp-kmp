package com.jewel.cosmicapp.shared.features.auth.di

import com.jewel.cosmicapp.shared.features.auth.data.AuthService
import com.jewel.cosmicapp.shared.features.auth.presentation.AuthViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::AuthService)
    viewModelOf(::AuthViewModel)
}
