package com.jewel.cosmicapp.shared.features.login.domain

interface LoginProvider {
    suspend fun signIn(): Result<LoginResult>
}
