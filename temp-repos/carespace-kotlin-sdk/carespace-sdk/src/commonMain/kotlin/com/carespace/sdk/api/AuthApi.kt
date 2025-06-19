package com.carespace.sdk.api

import com.carespace.sdk.http.CarespaceHttpClient
import com.carespace.sdk.models.*

/**
 * Authentication API interface
 */
interface AuthApi {
    suspend fun login(request: LoginRequest): ApiResponse<LoginResponse>
    suspend fun login(email: String, password: String): ApiResponse<LoginResponse>
    suspend fun refreshToken(request: RefreshTokenRequest): ApiResponse<LoginResponse>
    suspend fun refreshToken(refreshToken: String): ApiResponse<LoginResponse>
    suspend fun logout(request: LogoutRequest? = null): ApiResponse<Unit>
    suspend fun forgotPassword(request: ForgotPasswordRequest): ApiResponse<Unit>
    suspend fun forgotPassword(email: String): ApiResponse<Unit>
    suspend fun resetPassword(request: ResetPasswordRequest): ApiResponse<Unit>
    suspend fun resetPassword(token: String, newPassword: String): ApiResponse<Unit>
    suspend fun changePassword(request: ChangePasswordRequest): ApiResponse<Unit>
    suspend fun changePassword(currentPassword: String, newPassword: String): ApiResponse<Unit>
}

/**
 * Authentication API implementation
 */
class AuthApiImpl(private val httpClient: CarespaceHttpClient) : AuthApi {

    override suspend fun login(request: LoginRequest): ApiResponse<LoginResponse> {
        return httpClient.post("/auth/login", request)
    }

    override suspend fun login(email: String, password: String): ApiResponse<LoginResponse> {
        return login(LoginRequest(email, password))
    }

    override suspend fun refreshToken(request: RefreshTokenRequest): ApiResponse<LoginResponse> {
        return httpClient.post("/auth/refresh", request)
    }

    override suspend fun refreshToken(refreshToken: String): ApiResponse<LoginResponse> {
        return refreshToken(RefreshTokenRequest(refreshToken))
    }

    override suspend fun logout(request: LogoutRequest?): ApiResponse<Unit> {
        return httpClient.post("/auth/logout", request)
    }

    override suspend fun forgotPassword(request: ForgotPasswordRequest): ApiResponse<Unit> {
        return httpClient.post("/auth/forgot-password", request)
    }

    override suspend fun forgotPassword(email: String): ApiResponse<Unit> {
        return forgotPassword(ForgotPasswordRequest(email))
    }

    override suspend fun resetPassword(request: ResetPasswordRequest): ApiResponse<Unit> {
        return httpClient.post("/auth/reset-password", request)
    }

    override suspend fun resetPassword(token: String, newPassword: String): ApiResponse<Unit> {
        return resetPassword(ResetPasswordRequest(token, newPassword))
    }

    override suspend fun changePassword(request: ChangePasswordRequest): ApiResponse<Unit> {
        return httpClient.post("/auth/change-password", request)
    }

    override suspend fun changePassword(currentPassword: String, newPassword: String): ApiResponse<Unit> {
        return changePassword(ChangePasswordRequest(currentPassword, newPassword))
    }
}