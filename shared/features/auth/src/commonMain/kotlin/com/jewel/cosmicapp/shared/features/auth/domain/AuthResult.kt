package com.jewel.cosmicapp.shared.features.auth.domain

import kotlinx.serialization.Serializable

@Serializable
data class AuthResult(
    val token: String,
    val nonce: String
)
