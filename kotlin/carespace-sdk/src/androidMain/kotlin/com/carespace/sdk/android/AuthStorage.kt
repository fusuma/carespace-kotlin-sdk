package com.carespace.sdk.android

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Secure storage for authentication tokens on Android
 */
object AuthStorage {
    private const val PREFS_FILE_NAME = "carespace_sdk_prefs"
    private const val API_KEY_KEY = "api_key"
    private const val ACCESS_TOKEN_KEY = "access_token"
    private const val REFRESH_TOKEN_KEY = "refresh_token"
    private const val TOKEN_EXPIRY_KEY = "token_expiry"

    /**
     * Get encrypted shared preferences instance
     */
    private fun getEncryptedPrefs(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            PREFS_FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    /**
     * Save API key securely
     */
    fun saveApiKey(context: Context, apiKey: String) {
        getEncryptedPrefs(context)
            .edit()
            .putString(API_KEY_KEY, apiKey)
            .apply()
    }

    /**
     * Get stored API key
     */
    fun getApiKey(context: Context): String? {
        return getEncryptedPrefs(context).getString(API_KEY_KEY, null)
    }

    /**
     * Save access token securely
     */
    fun saveAccessToken(context: Context, accessToken: String) {
        getEncryptedPrefs(context)
            .edit()
            .putString(ACCESS_TOKEN_KEY, accessToken)
            .apply()
    }

    /**
     * Get stored access token
     */
    fun getAccessToken(context: Context): String? {
        return getEncryptedPrefs(context).getString(ACCESS_TOKEN_KEY, null)
    }

    /**
     * Save refresh token securely
     */
    fun saveRefreshToken(context: Context, refreshToken: String) {
        getEncryptedPrefs(context)
            .edit()
            .putString(REFRESH_TOKEN_KEY, refreshToken)
            .apply()
    }

    /**
     * Get stored refresh token
     */
    fun getRefreshToken(context: Context): String? {
        return getEncryptedPrefs(context).getString(REFRESH_TOKEN_KEY, null)
    }

    /**
     * Save token expiry time
     */
    fun saveTokenExpiry(context: Context, expiryTimeMillis: Long) {
        getEncryptedPrefs(context)
            .edit()
            .putLong(TOKEN_EXPIRY_KEY, expiryTimeMillis)
            .apply()
    }

    /**
     * Get token expiry time
     */
    fun getTokenExpiry(context: Context): Long {
        return getEncryptedPrefs(context).getLong(TOKEN_EXPIRY_KEY, 0L)
    }

    /**
     * Check if access token is expired
     */
    fun isTokenExpired(context: Context): Boolean {
        val expiryTime = getTokenExpiry(context)
        return expiryTime > 0 && System.currentTimeMillis() >= expiryTime
    }

    /**
     * Save complete authentication data
     */
    fun saveAuthData(
        context: Context,
        apiKey: String? = null,
        accessToken: String? = null,
        refreshToken: String? = null,
        expiryTimeMillis: Long? = null
    ) {
        val editor = getEncryptedPrefs(context).edit()
        
        apiKey?.let { editor.putString(API_KEY_KEY, it) }
        accessToken?.let { editor.putString(ACCESS_TOKEN_KEY, it) }
        refreshToken?.let { editor.putString(REFRESH_TOKEN_KEY, it) }
        expiryTimeMillis?.let { editor.putLong(TOKEN_EXPIRY_KEY, it) }
        
        editor.apply()
    }

    /**
     * Clear API key
     */
    fun clearApiKey(context: Context) {
        getEncryptedPrefs(context)
            .edit()
            .remove(API_KEY_KEY)
            .apply()
    }

    /**
     * Clear all authentication data
     */
    fun clearAllAuthData(context: Context) {
        getEncryptedPrefs(context)
            .edit()
            .remove(API_KEY_KEY)
            .remove(ACCESS_TOKEN_KEY)
            .remove(REFRESH_TOKEN_KEY)
            .remove(TOKEN_EXPIRY_KEY)
            .apply()
    }

    /**
     * Check if any authentication data exists
     */
    fun hasAuthData(context: Context): Boolean {
        val prefs = getEncryptedPrefs(context)
        return prefs.contains(API_KEY_KEY) || 
               prefs.contains(ACCESS_TOKEN_KEY) || 
               prefs.contains(REFRESH_TOKEN_KEY)
    }
}