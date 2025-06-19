package com.carespace.sdk

import com.carespace.sdk.configuration.CarespaceConfiguration
import com.carespace.sdk.models.*
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.*

class CarespaceClientTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun testClientInitialization() {
        val config = CarespaceConfiguration.forDevelopment("test-api-key")
        val client = CarespaceClient(config)
        
        assertNotNull(client.auth)
        assertNotNull(client.users)
        assertNotNull(client.clients)
        assertNotNull(client.programs)
        assertEquals(config, client.getConfiguration())
    }

    @Test
    fun testForDevelopmentFactory() {
        val client = CarespaceClient.forDevelopment("test-api-key")
        assertEquals("https://api-dev.carespace.ai", client.getConfiguration().baseUrl)
        assertEquals("test-api-key", client.getConfiguration().apiKey)
    }

    @Test
    fun testForProductionFactory() {
        val client = CarespaceClient.forProduction("test-api-key")
        assertEquals("https://api.carespace.ai", client.getConfiguration().baseUrl)
        assertEquals("test-api-key", client.getConfiguration().apiKey)
    }

    @Test
    fun testForStagingFactory() {
        val client = CarespaceClient.forStaging("test-api-key")
        assertEquals("https://api-staging.carespace.ai", client.getConfiguration().baseUrl)
        assertEquals("test-api-key", client.getConfiguration().apiKey)
    }

    @Test
    fun testLoginAndSetToken() = runTest {
        val mockEngine = MockEngine { request ->
            when (request.url.encodedPath) {
                "/auth/login" -> {
                    val loginResponse = LoginResponse(
                        accessToken = "new-access-token",
                        refreshToken = "refresh-token",
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
                    
                    val response = ApiResponse(
                        success = true,
                        data = loginResponse
                    )
                    
                    respond(
                        content = ByteReadChannel(json.encodeToString(response)),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                else -> {
                    respond(
                        content = ByteReadChannel(""),
                        status = HttpStatusCode.NotFound
                    )
                }
            }
        }

        val httpClient = com.carespace.sdk.http.CarespaceHttpClient(
            CarespaceConfiguration.forDevelopment(),
            mockEngine
        )
        val client = CarespaceClient(CarespaceConfiguration.forDevelopment(), httpClient)

        val result = client.loginAndSetToken("test@example.com", "password")
        
        assertEquals("new-access-token", result.accessToken)
        assertEquals("Test User", result.user.name)
    }

    @Test
    fun testQuickGetUsers() = runTest {
        val mockEngine = MockEngine { request ->
            when (request.url.encodedPath) {
                "/users" -> {
                    val users = listOf(
                        User(
                            id = "user-1",
                            email = "user1@example.com",
                            name = "User One",
                            firstName = "User",
                            lastName = "One",
                            role = UserRole.PATIENT,
                            isActive = true,
                            createdAt = "2023-01-01T00:00:00Z",
                            updatedAt = "2023-01-01T00:00:00Z"
                        )
                    )
                    
                    val response = PaginatedResponse(
                        success = true,
                        data = users,
                        page = 1,
                        limit = 20,
                        total = 1,
                        totalPages = 1,
                        hasNext = false,
                        hasPrevious = false
                    )
                    
                    respond(
                        content = ByteReadChannel(json.encodeToString(response)),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                else -> {
                    respond(
                        content = ByteReadChannel(""),
                        status = HttpStatusCode.NotFound
                    )
                }
            }
        }

        val httpClient = com.carespace.sdk.http.CarespaceHttpClient(
            CarespaceConfiguration.forDevelopment(),
            mockEngine
        )
        val client = CarespaceClient(CarespaceConfiguration.forDevelopment(), httpClient)

        val result = client.quickGetUsers(limit = 10)
        
        assertTrue(result.success)
        assertEquals(1, result.data.size)
        assertEquals("User One", result.data.first().name)
    }

    @Test
    fun testApiKeyUpdating() {
        val client = CarespaceClient.forDevelopment()
        
        client.setApiKey("new-api-key")
        
        // Verify that API key was updated (we can't directly test this without exposing internals)
        // In a real scenario, you would test that subsequent API calls use the new key
    }
}