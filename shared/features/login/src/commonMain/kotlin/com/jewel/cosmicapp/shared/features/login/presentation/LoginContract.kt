package com.jewel.cosmicapp.shared.features.login.presentation

data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAuthenticated: Boolean = false
)

sealed interface LoginEvent {
    data object SignInWithGoogle : LoginEvent
    data object SignInWithApple : LoginEvent
}

sealed interface LoginEffect {
    data object NavigateToDashboard : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}
