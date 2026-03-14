package com.jewel.cosmicapp.ui.auth

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
import com.jewel.cosmicapp.shared.features.auth.presentation.AuthEffect
import com.jewel.cosmicapp.shared.features.auth.presentation.AuthEvent
import com.jewel.cosmicapp.shared.features.auth.presentation.AuthNavigation
import com.jewel.cosmicapp.shared.features.auth.presentation.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthScreen(
    navigation: AuthNavigation,
    viewModel: AuthViewModel = koinViewModel() // Access the shared ViewModel
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is AuthEffect.NavigateToDashboard -> navigation.onLoginSuccess()
                is AuthEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
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
                        onClick = { viewModel.onEvent(AuthEvent.SignInWithGoogle) },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Sign in with Google")
                    }
                }
            }
        }
    }
}
