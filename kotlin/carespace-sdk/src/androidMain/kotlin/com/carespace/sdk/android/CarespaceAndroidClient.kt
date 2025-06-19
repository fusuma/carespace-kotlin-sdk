package com.carespace.sdk.android

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.carespace.sdk.CarespaceClient
import com.carespace.sdk.configuration.CarespaceConfiguration
import kotlinx.coroutines.launch

/**
 * Android-specific Carespace client with lifecycle awareness
 */
class CarespaceAndroidClient(
    context: Context,
    configuration: CarespaceConfiguration = CarespaceConfiguration.forDevelopment()
) : CarespaceClient(configuration) {

    private val appContext = context.applicationContext

    /**
     * Execute API call with lifecycle awareness
     */
    fun <T> executeWithLifecycle(
        lifecycleOwner: LifecycleOwner,
        onSuccess: (T) -> Unit = {},
        onError: (Exception) -> Unit = {},
        apiCall: suspend () -> T
    ) {
        lifecycleOwner.lifecycleScope.launch {
            try {
                val result = apiCall()
                onSuccess(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    /**
     * Check if device has network connectivity
     */
    fun isNetworkAvailable(): Boolean {
        return NetworkUtils.isNetworkAvailable(appContext)
    }

    /**
     * Get cached authentication token from shared preferences
     */
    fun getCachedApiKey(): String? {
        return AuthStorage.getApiKey(appContext)
    }

    /**
     * Cache authentication token in shared preferences
     */
    fun cacheApiKey(apiKey: String) {
        AuthStorage.saveApiKey(appContext, apiKey)
        setApiKey(apiKey)
    }

    /**
     * Clear cached authentication token
     */
    fun clearCachedApiKey() {
        AuthStorage.clearApiKey(appContext)
    }

    companion object {
        /**
         * Create Android client for development
         */
        fun forDevelopment(context: Context, apiKey: String? = null): CarespaceAndroidClient {
            return CarespaceAndroidClient(context, CarespaceConfiguration.forDevelopment(apiKey))
        }

        /**
         * Create Android client for production
         */
        fun forProduction(context: Context, apiKey: String): CarespaceAndroidClient {
            return CarespaceAndroidClient(context, CarespaceConfiguration.forProduction(apiKey))
        }

        /**
         * Create Android client for staging
         */
        fun forStaging(context: Context, apiKey: String): CarespaceAndroidClient {
            return CarespaceAndroidClient(context, CarespaceConfiguration.forStaging(apiKey))
        }
    }
}