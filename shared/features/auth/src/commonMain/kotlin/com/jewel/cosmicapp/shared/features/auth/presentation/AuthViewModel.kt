package com.jewel.cosmicapp.shared.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jewel.cosmicapp.shared.features.auth.data.AuthService
import com.jewel.cosmicapp.shared.features.auth.domain.AuthProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authProvider: AuthProvider,
    private val authService: AuthService
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    private val _effects = Channel<AuthEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.SignInWithGoogle -> handleSignIn()
            AuthEvent.SignInWithApple -> handleSignIn()
        }
    }

    private fun handleSignIn() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            authProvider.signIn()
                .onSuccess { authResult ->
                    authService.validateToken(authResult)
                        .onSuccess {
                            _state.update { it.copy(isLoading = false, isAuthenticated = true) }
                            _effects.send(AuthEffect.NavigateToDashboard)
                        }
                        .onFailure { error ->
                            _state.update { it.copy(isLoading = false, error = error.message) }
                            _effects.send(AuthEffect.ShowError(error.message ?: "Validation failed"))
                        }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                    _effects.send(AuthEffect.ShowError(error.message ?: "Sign in failed"))
                }
        }
    }
}
