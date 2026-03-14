package com.jewel.cosmicapp.shared.features.auth.presentation

data class AuthState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAuthenticated: Boolean = false
)

sealed interface AuthEvent {
    data object SignInWithGoogle : AuthEvent
    data object SignInWithApple : AuthEvent
}

sealed interface AuthEffect {
    data object NavigateToDashboard : AuthEffect
    data class ShowError(val message: String) : AuthEffect
}
