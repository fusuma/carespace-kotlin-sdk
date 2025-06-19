package com.carespace.sdk.api

import com.carespace.sdk.configuration.CarespaceConfiguration
import com.carespace.sdk.http.CarespaceHttpClient
import com.carespace.sdk.models.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.*

class AuthApiTest {
    
    private val json = Json { ignoreUnknownKeys = true }

    private fun createMockAuthApi(): Pair<AuthApi, MockEngine> {
        val mockEngine = MockEngine { request ->
            when (request.url.encodedPath) {
                "/auth/login" -> {
                    val loginResponse = LoginResponse(
                        accessToken = "access-token-123",
                        refreshToken = "refresh-token-123",
                        expiresIn = 3600,
                        user = User(
                            id = "user-123",
                            email = "test@example.com",
                            name = "Test User",
                            firstName = "Test",
                            lastName = "User", 
                            role = UserRole.PATIENT,
                            isActive = true,
                            createdAt = "2023-01-01T00:00:00Z",
                            updatedAt = "2023-01-01T00:00:00Z"
                        )
                    )
                    
                    val response = ApiResponse(success = true, data = loginResponse)
                    
                    respond(
                        content = ByteReadChannel(json.encodeToString(response)),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                
                "/auth/refresh" -> {
                    val loginResponse = LoginResponse(
                        accessToken = "new-access-token-123",
                        refreshToken = "new-refresh-token-123",
                        expiresIn = 3600,
                        user = User(
                            id = "user-123",
                            email = "test@example.com",
                            name = "Test User",
                            firstName = "Test",
                            lastName = "User",
                            role = UserRole.PATIENT,
                            isActive = true,
                            createdAt = "2023-01-01T00:00:00Z",
                            updatedAt = "2023-01-01T00:00:00Z"
                        )
                    )
                    
                    val response = ApiResponse(success = true, data = loginResponse)
                    
                    respond(
                        content = ByteReadChannel(json.encodeToString(response)),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                
                "/auth/logout" -> {
                    val response = ApiResponse(success = true, data = Unit)
                    
                    respond(
                        content = ByteReadChannel(json.encodeToString(response)),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                
                "/auth/forgot-password" -> {
                    val response = ApiResponse(success = true, data = Unit)
                    
                    respond(
                        content = ByteReadChannel(json.encodeToString(response)),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                
                else -> {
                    respond(
                        content = ByteReadChannel("Not Found"),
                        status = HttpStatusCode.NotFound
                    )
                }
            }
        }

        val httpClient = CarespaceHttpClient(CarespaceConfiguration.forDevelopment(), mockEngine)
        val authApi = AuthApiImpl(httpClient)
        
        return Pair(authApi, mockEngine)
    }

    @Test
    fun testLogin() = runTest {
        val (authApi, _) = createMockAuthApi()
        
        val result = authApi.login("test@example.com", "password")
        
        assertTrue(result.success)
        assertNotNull(result.data)
        assertEquals("access-token-123", result.data!!.accessToken)
        assertEquals("refresh-token-123", result.data!!.refreshToken)
        assertEquals("Test User", result.data!!.user.name)
    }

    @Test
    fun testLoginWithRequest() = runTest {
        val (authApi, _) = createMockAuthApi()
        
        val request = LoginRequest(
            email = "test@example.com",
            password = "password"
        )
        
        val result = authApi.login(request)
        
        assertTrue(result.success)
        assertNotNull(result.data)
        assertEquals("access-token-123", result.data!!.accessToken)
    }

    @Test
    fun testRefreshToken() = runTest {
        val (authApi, _) = createMockAuthApi()
        
        val result = authApi.refreshToken("refresh-token-123")
        
        assertTrue(result.success)
        assertNotNull(result.data)
        assertEquals("new-access-token-123", result.data!!.accessToken)
        assertEquals("new-refresh-token-123", result.data!!.refreshToken)
    }

    @Test
    fun testRefreshTokenWithRequest() = runTest {
        val (authApi, _) = createMockAuthApi()
        
        val request = RefreshTokenRequest(refreshToken = "refresh-token-123")
        val result = authApi.refreshToken(request)
        
        assertTrue(result.success)
        assertNotNull(result.data)
        assertEquals("new-access-token-123", result.data!!.accessToken)
    }

    @Test
    fun testLogout() = runTest {
        val (authApi, _) = createMockAuthApi()
        
        val result = authApi.logout()
        
        assertTrue(result.success)
    }

    @Test
    fun testLogoutWithRequest() = runTest {
        val (authApi, _) = createMockAuthApi()
        
        val request = LogoutRequest(refreshToken = "refresh-token-123")
        val result = authApi.logout(request)
        
        assertTrue(result.success)
    }

    @Test
    fun testForgotPassword() = runTest {
        val (authApi, _) = createMockAuthApi()
        
        val result = authApi.forgotPassword("test@example.com")
        
        assertTrue(result.success)
    }

    @Test
    fun testForgotPasswordWithRequest() = runTest {
        val (authApi, _) = createMockAuthApi()
        
        val request = ForgotPasswordRequest(email = "test@example.com")
        val result = authApi.forgotPassword(request)
        
        assertTrue(result.success)
    }
}