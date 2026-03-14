package com.jewel.cosmicapp.shared.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jewel.cosmicapp.shared.features.login.data.LoginService
import com.jewel.cosmicapp.shared.features.login.domain.LoginProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginProvider: LoginProvider,
    private val loginService: LoginService
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effects = Channel<LoginEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.SignInWithGoogle -> handleSignIn()
            LoginEvent.SignInWithApple -> handleSignIn()
        }
    }

    private fun handleSignIn() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            loginProvider.signIn()
                .onSuccess { result ->
                    loginService.validateToken(result)
                        .onSuccess {
                            _state.update { it.copy(isLoading = false, isAuthenticated = true) }
                            _effects.send(LoginEffect.NavigateToDashboard)
                        }
                        .onFailure { error ->
                            _state.update { it.copy(isLoading = false, error = error.message) }
                            _effects.send(LoginEffect.ShowError(error.message ?: "Validation failed"))
                        }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                    _effects.send(LoginEffect.ShowError(error.message ?: "Sign in failed"))
                }
        }
    }
}
