package com.carespace.sdk.exceptions

import io.ktor.http.*

/**
 * Base exception class for all Carespace SDK errors
 */
open class CarespaceException(
    message: String,
    val statusCode: HttpStatusCode? = null,
    val errorCode: String? = null,
    val errorDetails: Any? = null,
    cause: Throwable? = null
) : Exception(message, cause)

/**
 * Exception thrown when authentication fails
 */
class CarespaceAuthenticationException(
    message: String = "Authentication failed. Please check your API key.",
    cause: Throwable? = null
) : CarespaceException(
    message = message,
    statusCode = HttpStatusCode.Unauthorized,
    errorCode = "AUTHENTICATION_FAILED",
    cause = cause
)

/**
 * Exception thrown when authorization fails (insufficient permissions)
 */
class CarespaceAuthorizationException(
    message: String = "Access denied. You don't have permission to access this resource.",
    cause: Throwable? = null
) : CarespaceException(
    message = message,
    statusCode = HttpStatusCode.Forbidden,
    errorCode = "AUTHORIZATION_FAILED",
    cause = cause
)

/**
 * Exception thrown when a requested resource is not found
 */
class CarespaceNotFoundException(
    message: String = "The requested resource was not found.",
    cause: Throwable? = null
) : CarespaceException(
    message = message,
    statusCode = HttpStatusCode.NotFound,
    errorCode = "RESOURCE_NOT_FOUND",
    cause = cause
)

/**
 * Exception thrown when request validation fails
 */
class CarespaceValidationException(
    message: String = "Bad request. Please check your request data.",
    validationErrors: Any? = null,
    cause: Throwable? = null
) : CarespaceException(
    message = message,
    statusCode = HttpStatusCode.BadRequest,
    errorCode = "VALIDATION_FAILED",
    errorDetails = validationErrors,
    cause = cause
)

/**
 * Exception thrown when rate limit is exceeded
 */
class CarespaceRateLimitException(
    message: String = "Rate limit exceeded. Please try again later.",
    val retryAfterSeconds: Long? = null,
    cause: Throwable? = null
) : CarespaceException(
    message = message,
    statusCode = HttpStatusCode.TooManyRequests,
    errorCode = "RATE_LIMIT_EXCEEDED",
    cause = cause
)

/**
 * Exception thrown when server encounters an internal error
 */
class CarespaceServerException(
    message: String = "Internal server error occurred.",
    cause: Throwable? = null
) : CarespaceException(
    message = message,
    statusCode = HttpStatusCode.InternalServerError,
    errorCode = "SERVER_ERROR",
    cause = cause
)

/**
 * Exception thrown when network connectivity issues occur
 */
class CarespaceNetworkException(
    message: String = "Network error occurred. Please check your internet connection.",
    cause: Throwable? = null
) : CarespaceException(
    message = message,
    errorCode = "NETWORK_ERROR",
    cause = cause
)

/**
 * Exception thrown when request times out
 */
class CarespaceTimeoutException(
    message: String = "Request timed out. Please try again.",
    cause: Throwable? = null
) : CarespaceException(
    message = message,
    errorCode = "TIMEOUT_ERROR",
    cause = cause
)