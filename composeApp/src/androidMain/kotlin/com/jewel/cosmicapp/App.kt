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
import com.jewel.cosmicapp.shared.features.login.presentation.LoginNavigation
import com.jewel.cosmicapp.shared.features.login.presentation.LoginRoutes
import com.jewel.cosmicapp.shared.features.login.presentation.loginGraph
import com.jewel.cosmicapp.ui.login.LoginScreen

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
                startDestination = LoginRoutes.LOGIN_MAIN
            ) {
                loginGraph {
                    LoginScreen(
                        navigation = object : LoginNavigation {
                            override fun onLoginSuccess() {
                                navController.navigate("home") {
                                    popUpTo(LoginRoutes.LOGIN_MAIN) { inclusive = true }
                                }
                            }

                            override fun onSignUpClicked() {
                                // Handle sign up navigation if needed
                            }
                        }
                    )
                }

                composable("home") {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Text("Welcome to Home Screen!")
                    }
                }
            }
        }
    }
}
