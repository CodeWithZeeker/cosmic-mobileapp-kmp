package com.jewel.cosmicapp.shared.features.auth.data

import com.jewel.cosmicapp.shared.features.auth.domain.AuthResult
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class AuthService(private val httpClient: HttpClient) {
    suspend fun validateToken(authResult: AuthResult): Result<Unit> {
        return try {
            val response = httpClient.post("https://api.cosmicapp.com/auth/validate") {
                contentType(ContentType.Application.Json)
                setBody(authResult)
            }
            if (response.status.isSuccess()) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Validation failed: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
