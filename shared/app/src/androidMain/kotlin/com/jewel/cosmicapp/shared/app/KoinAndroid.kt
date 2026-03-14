package com.jewel.cosmicapp.shared.app

import android.content.Context
import com.jewel.cosmicapp.shared.app.di.appModule
import com.jewel.cosmicapp.shared.features.login.domain.AndroidLoginProvider
import com.jewel.cosmicapp.shared.features.login.domain.LoginProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(
            appModule,
            module {
                single<LoginProvider> { AndroidLoginProvider(get()) }
            }
        )
    }
}
