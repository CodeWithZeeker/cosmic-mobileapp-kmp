package com.jewel.cosmicapp.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jewel.cosmicapp.shared.features.login.presentation.LoginEffect
import com.jewel.cosmicapp.shared.features.login.presentation.LoginEvent
import com.jewel.cosmicapp.shared.features.login.presentation.LoginNavigation
import com.jewel.cosmicapp.shared.features.login.presentation.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    navigation: LoginNavigation,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is LoginEffect.NavigateToDashboard -> navigation.onLoginSuccess()
                is LoginEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "CosmicAPP",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Button(
                        onClick = { viewModel.onEvent(LoginEvent.SignInWithGoogle) },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Sign in with Google")
                    }
                }
            }
        }
    }
}
