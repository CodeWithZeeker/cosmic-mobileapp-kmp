package com.jewel.cosmicapp.shared.features.login.domain

class IosLoginProvider : LoginProvider {
    override suspend fun signIn(): Result<LoginResult> {
        // TODO: Implement Apple Sign In using AuthenticationServices
        return Result.failure(Exception("Not implemented yet"))
    }
}
