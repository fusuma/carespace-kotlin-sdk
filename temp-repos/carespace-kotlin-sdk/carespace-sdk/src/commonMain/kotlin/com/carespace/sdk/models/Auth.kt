package com.carespace.sdk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String
)

@Serializable
data class LoginResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("token_type") val tokenType: String = "Bearer",
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("user") val user: User
)

@Serializable
data class RefreshTokenRequest(
    @SerialName("refresh_token") val refreshToken: String
)

@Serializable
data class LogoutRequest(
    @SerialName("refresh_token") val refreshToken: String? = null
)

@Serializable
data class ForgotPasswordRequest(
    @SerialName("email") val email: String
)

@Serializable
data class ResetPasswordRequest(
    @SerialName("token") val token: String,
    @SerialName("new_password") val newPassword: String
)

@Serializable
data class ChangePasswordRequest(
    @SerialName("current_password") val currentPassword: String,
    @SerialName("new_password") val newPassword: String
)