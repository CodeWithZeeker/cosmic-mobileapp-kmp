package com.jewel.cosmicapp.shared.features.login.domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginResult(
    val token: String,
    val nonce: String
)
