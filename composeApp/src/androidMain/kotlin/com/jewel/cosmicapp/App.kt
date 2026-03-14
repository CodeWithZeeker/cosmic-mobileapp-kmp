package com.jewel.cosmicapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jewel.cosmicapp.shared.features.auth.presentation.AuthNavigation
import com.jewel.cosmicapp.shared.features.auth.presentation.AuthRoutes
import com.jewel.cosmicapp.shared.features.auth.presentation.authGraph
import com.jewel.cosmicapp.ui.auth.AuthScreen

@Composable
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = AuthRoutes.AUTH_MAIN
            ) {
                authGraph {
                    AuthScreen(
                        navigation = object : AuthNavigation {
                            override fun onLoginSuccess() {
                                navController.navigate("home") {
                                    popUpTo(AuthRoutes.AUTH_MAIN) { inclusive = true }
                                }
                            }

                            override fun onSignUpClicked() {
                                // Handle sign up navigation if needed
                            }
                        }
                    )
                }

//                composable("home") {
//                    Surface(modifier = Modifier.fillMaxSize()) {
//                        Text("Welcome to Home Screen!")
//                    }
//                }
            }
        }
    }
}
