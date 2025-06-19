package com.carespace.sdk.configuration

import io.ktor.client.plugins.logging.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Configuration for the Carespace SDK
 */
data class CarespaceConfiguration(
    val baseUrl: String = "https://api-dev.carespace.ai",
    val apiKey: String? = null,
    val timeout: Duration = 30.seconds,
    val maxRetryAttempts: Int = 3,
    val retryDelay: Duration = 1.seconds,
    val enableRetry: Boolean = true,
    val enableLogging: Boolean = true,
    val logLevel: LogLevel = LogLevel.INFO,
    val userAgent: String = "CarespaceSDK/1.0.0 (Kotlin)",
    val defaultHeaders: Map<String, String> = emptyMap()
) {
    companion object {
        /**
         * Create configuration for development environment
         */
        fun forDevelopment(apiKey: String? = null): CarespaceConfiguration {
            return CarespaceConfiguration(
                baseUrl = "https://api-dev.carespace.ai",
                apiKey = apiKey,
                logLevel = LogLevel.ALL
            )
        }

        /**
         * Create configuration for production environment
         */
        fun forProduction(apiKey: String): CarespaceConfiguration {
            return CarespaceConfiguration(
                baseUrl = "https://api.carespace.ai",
                apiKey = apiKey,
                logLevel = LogLevel.NONE
            )
        }

        /**
         * Create configuration for staging environment
         */
        fun forStaging(apiKey: String): CarespaceConfiguration {
            return CarespaceConfiguration(
                baseUrl = "https://api-staging.carespace.ai",
                apiKey = apiKey,
                logLevel = LogLevel.INFO
            )
        }
    }

    /**
     * Validate the configuration
     */
    fun validate() {
        require(baseUrl.isNotBlank()) { "Base URL cannot be blank" }
        require(timeout.isPositive()) { "Timeout must be positive" }
        require(maxRetryAttempts >= 0) { "Max retry attempts cannot be negative" }
        require(retryDelay.isPositive()) { "Retry delay must be positive" }
        require(userAgent.isNotBlank()) { "User agent cannot be blank" }
    }

    /**
     * Create a copy with updated API key
     */
    fun withApiKey(apiKey: String): CarespaceConfiguration {
        return copy(apiKey = apiKey)
    }

    /**
     * Create a copy with additional headers
     */
    fun withHeaders(headers: Map<String, String>): CarespaceConfiguration {
        return copy(defaultHeaders = defaultHeaders + headers)
    }
}