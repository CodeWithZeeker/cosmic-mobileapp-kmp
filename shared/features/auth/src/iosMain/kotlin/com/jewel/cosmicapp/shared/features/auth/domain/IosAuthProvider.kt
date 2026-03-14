package com.jewel.cosmicapp.shared.features.auth.domain

class IosAuthProvider : AuthProvider {
    override suspend fun signIn(): Result<AuthResult> {
        // TODO: Implement Apple Sign In using AuthenticationServices
        return Result.failure(Exception("Not implemented yet"))
    }
}
