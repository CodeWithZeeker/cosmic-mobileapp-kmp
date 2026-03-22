package com.jewel.cosmicapp.shared.app

import com.jewel.cosmicapp.shared.app.di.appModule
import com.jewel.cosmicapp.shared.features.login.domain.LoginProvider
import com.jewel.cosmicapp.shared.features.login.domain.IosLoginProvider
import com.jewel.cosmicapp.shared.features.login.presentation.LoginViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoinIos() {
    startKoin {
        modules(
            appModule,
            module {
                single<LoginProvider> { IosLoginProvider() }
            }
        )
    }
}

/**
 * A helper class to retrieve Koin dependencies from Swift.
 */
object KoinHelper : KoinComponent {
    fun getLoginViewModel(): LoginViewModel = get()
    // Add other dependencies needed by Swift here
}
