package com.jewel.cosmicapp.shared.app

import android.content.Context
import com.jewel.cosmicapp.shared.app.di.appModule
import com.jewel.cosmicapp.shared.features.auth.domain.AndroidAuthProvider
import com.jewel.cosmicapp.shared.features.auth.domain.AuthProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(
            appModule,
            module {
                single<AuthProvider> { AndroidAuthProvider(get()) }
            }
        )
    }
}
