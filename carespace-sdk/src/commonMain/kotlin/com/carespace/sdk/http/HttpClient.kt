package com.carespace.sdk.http

import com.carespace.sdk.configuration.CarespaceConfiguration
import com.carespace.sdk.exceptions.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlin.math.pow

/**
 * HTTP client wrapper for Carespace API
 */
class CarespaceHttpClient(
    private val configuration: CarespaceConfiguration,
    private val httpClientEngine: HttpClientEngine? = null
) {
    private var _apiKey: String? = configuration.apiKey

    private val client = HttpClient(httpClientEngine ?: getDefaultEngine()) {
        expectSuccess = false

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = false
            })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = configuration.timeout.inWholeMilliseconds
            connectTimeoutMillis = configuration.timeout.inWholeMilliseconds
            socketTimeoutMillis = configuration.timeout.inWholeMilliseconds
        }

        if (configuration.enableLogging) {
            install(Logging) {
                level = configuration.logLevel
                logger = Logger.DEFAULT
            }
        }

        install(Auth) {
            bearer {
                loadTokens {
                    _apiKey?.let { BearerTokens(it, "") }
                }
            }
        }

        install(DefaultRequest) {
            url(configuration.baseUrl)
            header(HttpHeaders.UserAgent, configuration.userAgent)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            
            configuration.defaultHeaders.forEach { (key, value) ->
                header(key, value)
            }
        }
    }

    /**
     * Set API key for authentication
     */
    fun setApiKey(apiKey: String) {
        _apiKey = apiKey
    }

    /**
     * Perform GET request with retry logic
     */
    suspend inline fun <reified T> get(
        endpoint: String,
        parameters: Map<String, Any?> = emptyMap()
    ): T {
        return executeWithRetry {
            val response = client.get(endpoint) {
                parameters.forEach { (key, value) ->
                    if (value != null) {
                        parameter(key, value.toString())
                    }
                }
            }
            handleResponse<T>(response)
        }
    }

    /**
     * Perform POST request with retry logic
     */
    suspend inline fun <reified T> post(
        endpoint: String,
        body: Any? = null
    ): T {
        return executeWithRetry {
            val response = client.post(endpoint) {
                if (body != null) {
                    setBody(body)
                }
            }
            handleResponse<T>(response)
        }
    }

    /**
     * Perform PUT request with retry logic
     */
    suspend inline fun <reified T> put(
        endpoint: String,
        body: Any? = null
    ): T {
        return executeWithRetry {
            val response = client.put(endpoint) {
                if (body != null) {
                    setBody(body)
                }
            }
            handleResponse<T>(response)
        }
    }

    /**
     * Perform PATCH request with retry logic
     */
    suspend inline fun <reified T> patch(
        endpoint: String,
        body: Any? = null
    ): T {
        return executeWithRetry {
            val response = client.patch(endpoint) {
                if (body != null) {
                    setBody(body)
                }
            }
            handleResponse<T>(response)
        }
    }

    /**
     * Perform DELETE request with retry logic
     */
    suspend inline fun <reified T> delete(endpoint: String): T {
        return executeWithRetry {
            val response = client.delete(endpoint)
            handleResponse<T>(response)
        }
    }

    /**
     * Perform DELETE request without return type
     */
    suspend fun delete(endpoint: String) {
        executeWithRetry {
            val response = client.delete(endpoint)
            if (!response.status.isSuccess()) {
                throw mapToCarespaceException(response)
            }
        }
    }

    /**
     * Handle HTTP response and map to appropriate type or exception
     */
    suspend inline fun <reified T> handleResponse(response: HttpResponse): T {
        return when {
            response.status.isSuccess() -> {
                if (T::class == Unit::class) {
                    Unit as T
                } else {
                    response.body<T>()
                }
            }
            else -> throw mapToCarespaceException(response)
        }
    }

    /**
     * Map HTTP response to appropriate Carespace exception
     */
    private suspend fun mapToCarespaceException(response: HttpResponse): CarespaceException {
        val body = try {
            response.bodyAsText()
        } catch (e: Exception) {
            "Unknown error"
        }

        return when (response.status) {
            HttpStatusCode.Unauthorized -> CarespaceAuthenticationException()
            HttpStatusCode.Forbidden -> CarespaceAuthorizationException()
            HttpStatusCode.NotFound -> CarespaceNotFoundException()
            HttpStatusCode.BadRequest -> CarespaceValidationException()
            HttpStatusCode.TooManyRequests -> {
                val retryAfter = response.headers["Retry-After"]?.toLongOrNull()
                CarespaceRateLimitException(retryAfterSeconds = retryAfter)
            }
            HttpStatusCode.InternalServerError -> CarespaceServerException()
            else -> CarespaceException(
                message = "HTTP request failed with status ${response.status}",
                statusCode = response.status
            )
        }
    }

    /**
     * Execute request with retry logic
     */
    private suspend fun <T> executeWithRetry(operation: suspend () -> T): T {
        if (!configuration.enableRetry) {
            return operation()
        }

        var lastException: Exception? = null
        repeat(configuration.maxRetryAttempts + 1) { attempt ->
            try {
                return operation()
            } catch (e: Exception) {
                lastException = e
                
                if (attempt < configuration.maxRetryAttempts && shouldRetry(e)) {
                    val delayMs = configuration.retryDelay.inWholeMilliseconds * 
                                 2.0.pow(attempt).toLong()
                    delay(delayMs)
                } else {
                    throw e
                }
            }
        }
        
        throw lastException ?: CarespaceException("Unknown error during retry")
    }

    /**
     * Determine if exception should trigger a retry
     */
    private fun shouldRetry(exception: Exception): Boolean {
        return when (exception) {
            is CarespaceNetworkException -> true
            is CarespaceTimeoutException -> true
            is CarespaceServerException -> true
            is CarespaceException -> exception.statusCode?.let { 
                it.value >= 500 
            } ?: false
            else -> false
        }
    }

    /**
     * Get default HTTP client engine for the platform
     */
    private fun getDefaultEngine(): HttpClientEngine? = null

    /**
     * Close the HTTP client
     */
    fun close() {
        client.close()
    }
}