package com.jewel.cosmicapp.shared.features.login.domain

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.jewel.cosmicapp.shared.features.login.util.generateNonce

class AndroidLoginProvider(private val context: Context) : LoginProvider {
    override suspend fun signIn(): Result<LoginResult> {
        return try {
            val nonce = generateNonce()
            val credentialManager = CredentialManager.create(context)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
            .setServerClientId("928106305774-tan6ink6641k78v39tfm1jp1hivotpt3.apps.googleusercontent.com") // Replace with actual client ID
                .setNonce(nonce)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)

            Result.success(LoginResult(googleIdTokenCredential.idToken, nonce))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
