package com.jewel.cosmicapp.shared.features.login.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object LoginRoutes {
    const val LOGIN_MAIN = "login_screen"
}

fun NavGraphBuilder.loginGraph(
    content: @Composable () -> Unit
) {
    composable(LoginRoutes.LOGIN_MAIN) {
        content()
    }
}
