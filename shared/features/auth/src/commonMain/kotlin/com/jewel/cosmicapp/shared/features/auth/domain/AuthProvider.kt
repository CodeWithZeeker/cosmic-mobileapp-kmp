package com.jewel.cosmicapp.shared.features.auth.domain

interface AuthProvider {
    suspend fun signIn(): Result<AuthResult>
}
