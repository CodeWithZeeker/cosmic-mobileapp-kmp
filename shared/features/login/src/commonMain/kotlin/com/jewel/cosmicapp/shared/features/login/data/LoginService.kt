package com.jewel.cosmicapp.shared.features.login.data

import com.jewel.cosmicapp.shared.features.login.domain.LoginResult
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class LoginService(private val httpClient: HttpClient) {
    suspend fun validateToken(loginResult: LoginResult): Result<Unit> {
        return try {
            val response = httpClient.post("https://api.cosmicapp.com/auth/validate") {
                contentType(ContentType.Application.Json)
                setBody(loginResult)
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
