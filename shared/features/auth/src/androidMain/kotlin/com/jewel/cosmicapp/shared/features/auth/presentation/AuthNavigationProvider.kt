package com.jewel.cosmicapp.shared.features.auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

// We define the ROUTE string here so it's shared,
// but the actual Composable call happens in composeApp
object AuthRoutes {
    const val AUTH_MAIN = "auth_screen"
}

fun NavGraphBuilder.authGraph(
    content: @Composable () -> Unit
) {
    composable(AuthRoutes.AUTH_MAIN) {
        content()
    }
}
