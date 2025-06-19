package com.carespace.sdk

import com.carespace.sdk.api.*
import com.carespace.sdk.configuration.CarespaceConfiguration
import com.carespace.sdk.http.CarespaceHttpClient
import com.carespace.sdk.models.*

/**
 * Main Carespace SDK client
 */
class CarespaceClient(
    private val configuration: CarespaceConfiguration = CarespaceConfiguration.forDevelopment(),
    httpClient: CarespaceHttpClient? = null
) {
    private val _httpClient: CarespaceHttpClient = httpClient ?: CarespaceHttpClient(configuration)

    // API endpoints
    val auth: AuthApi = AuthApiImpl(_httpClient)
    val users: UsersApi = UsersApiImpl(_httpClient)
    val clients: ClientsApi = ClientsApiImpl(_httpClient)
    val programs: ProgramsApi = ProgramsApiImpl(_httpClient)

    /**
     * Set API key for authentication
     */
    fun setApiKey(apiKey: String) {
        _httpClient.setApiKey(apiKey)
    }

    /**
     * Login and automatically set the access token
     */
    suspend fun loginAndSetToken(email: String, password: String): LoginResponse {
        val response = auth.login(email, password)
        if (response.success && response.data != null) {
            setApiKey(response.data.accessToken)
            return response.data
        } else {
            throw IllegalStateException("Login failed: ${response.error ?: response.message}")
        }
    }

    /**
     * Quick method to get users with minimal parameters
     */
    suspend fun quickGetUsers(limit: Int = 20, search: String? = null): PaginatedResponse<User> {
        return users.getUsers(limit = limit, search = search)
    }

    /**
     * Quick method to get clients with minimal parameters
     */
    suspend fun quickGetClients(limit: Int = 20, search: String? = null): PaginatedResponse<Client> {
        return clients.getClients(limit = limit, search = search)
    }

    /**
     * Quick method to get programs with minimal parameters
     */
    suspend fun quickGetPrograms(limit: Int = 20, category: ProgramCategory? = null): PaginatedResponse<Program> {
        return programs.getPrograms(limit = limit, category = category)
    }

    /**
     * Perform a health check to verify API connectivity
     */
    suspend fun healthCheck(): Boolean {
        return try {
            if (configuration.apiKey != null) {
                users.getUserProfile()
                true
            } else {
                users.getUsers(limit = 1)
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Get current configuration
     */
    fun getConfiguration(): CarespaceConfiguration = configuration

    /**
     * Close the client and cleanup resources
     */
    fun close() {
        _httpClient.close()
    }

    companion object {
        /**
         * Create client for development environment
         */
        fun forDevelopment(apiKey: String? = null): CarespaceClient {
            return CarespaceClient(CarespaceConfiguration.forDevelopment(apiKey))
        }

        /**
         * Create client for production environment
         */
        fun forProduction(apiKey: String): CarespaceClient {
            return CarespaceClient(CarespaceConfiguration.forProduction(apiKey))
        }

        /**
         * Create client for staging environment
         */
        fun forStaging(apiKey: String): CarespaceClient {
            return CarespaceClient(CarespaceConfiguration.forStaging(apiKey))
        }

        /**
         * Create client with custom configuration
         */
        fun withConfiguration(configuration: CarespaceConfiguration): CarespaceClient {
            configuration.validate()
            return CarespaceClient(configuration)
        }

        /**
         * Create client with API key and custom base URL
         */
        fun withApiKey(apiKey: String, baseUrl: String? = null): CarespaceClient {
            val config = CarespaceConfiguration.forDevelopment(apiKey).copy(
                baseUrl = baseUrl ?: "https://api-dev.carespace.ai"
            )
            return CarespaceClient(config)
        }
    }
}