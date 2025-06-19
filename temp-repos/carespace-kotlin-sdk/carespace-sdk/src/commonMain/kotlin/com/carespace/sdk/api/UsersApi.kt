package com.carespace.sdk.api

import com.carespace.sdk.http.CarespaceHttpClient
import com.carespace.sdk.models.*

/**
 * Users API interface
 */
interface UsersApi {
    suspend fun getUsers(
        page: Int = 1,
        limit: Int = 20,
        search: String? = null,
        role: UserRole? = null,
        isActive: Boolean? = null
    ): PaginatedResponse<User>

    suspend fun getUser(userId: String): ApiResponse<User>
    suspend fun getUserProfile(): ApiResponse<User>
    suspend fun createUser(request: CreateUserRequest): ApiResponse<User>
    suspend fun updateUser(userId: String, request: UpdateUserRequest): ApiResponse<User>
    suspend fun updateUserProfile(request: UpdateUserRequest): ApiResponse<User>
    suspend fun deleteUser(userId: String)
    suspend fun deactivateUser(userId: String): ApiResponse<Unit>
    suspend fun activateUser(userId: String): ApiResponse<Unit>
}

/**
 * Users API implementation
 */
class UsersApiImpl(private val httpClient: CarespaceHttpClient) : UsersApi {

    override suspend fun getUsers(
        page: Int,
        limit: Int,
        search: String?,
        role: UserRole?,
        isActive: Boolean?
    ): PaginatedResponse<User> {
        val parameters = buildMap {
            put("page", page)
            put("limit", limit)
            search?.let { put("search", it) }
            role?.let { put("role", it.name.lowercase()) }
            isActive?.let { put("is_active", it) }
        }
        return httpClient.get("/users", parameters)
    }

    override suspend fun getUser(userId: String): ApiResponse<User> {
        require(userId.isNotBlank()) { "User ID cannot be blank" }
        return httpClient.get("/users/$userId")
    }

    override suspend fun getUserProfile(): ApiResponse<User> {
        return httpClient.get("/users/profile")
    }

    override suspend fun createUser(request: CreateUserRequest): ApiResponse<User> {
        return httpClient.post("/users", request)
    }

    override suspend fun updateUser(userId: String, request: UpdateUserRequest): ApiResponse<User> {
        require(userId.isNotBlank()) { "User ID cannot be blank" }
        return httpClient.put("/users/$userId", request)
    }

    override suspend fun updateUserProfile(request: UpdateUserRequest): ApiResponse<User> {
        return httpClient.put("/users/profile", request)
    }

    override suspend fun deleteUser(userId: String) {
        require(userId.isNotBlank()) { "User ID cannot be blank" }
        httpClient.delete("/users/$userId")
    }

    override suspend fun deactivateUser(userId: String): ApiResponse<Unit> {
        require(userId.isNotBlank()) { "User ID cannot be blank" }
        return httpClient.post("/users/$userId/deactivate")
    }

    override suspend fun activateUser(userId: String): ApiResponse<Unit> {
        require(userId.isNotBlank()) { "User ID cannot be blank" }
        return httpClient.post("/users/$userId/activate")
    }
}