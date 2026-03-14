package com.jewel.cosmicapp.shared.features.auth.domain

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.jewel.cosmicapp.shared.features.auth.util.generateNonce

class AndroidAuthProvider(private val context: Context) : AuthProvider {
    override suspend fun signIn(): Result<AuthResult> {
        return try {
            val nonce = generateNonce()
            val credentialManager = CredentialManager.create(context)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId("YOUR_WEB_CLIENT_ID") // Replace with actual client ID
                .setNonce(nonce)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)

            Result.success(AuthResult(googleIdTokenCredential.idToken, nonce))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
