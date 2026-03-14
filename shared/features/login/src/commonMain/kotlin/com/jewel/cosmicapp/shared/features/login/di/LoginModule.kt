package com.jewel.cosmicapp.shared.features.login.di

import com.jewel.cosmicapp.shared.features.login.data.LoginService
import com.jewel.cosmicapp.shared.features.login.presentation.LoginViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    singleOf(::LoginService)
    viewModelOf(::LoginViewModel)
}
