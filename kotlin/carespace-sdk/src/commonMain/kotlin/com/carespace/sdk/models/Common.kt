package com.carespace.sdk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("success") val success: Boolean,
    @SerialName("data") val data: T? = null,
    @SerialName("error") val error: String? = null,
    @SerialName("message") val message: String? = null
)

@Serializable
data class PaginatedResponse<T>(
    @SerialName("success") val success: Boolean,
    @SerialName("data") val data: List<T> = emptyList(),
    @SerialName("error") val error: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("page") val page: Int,
    @SerialName("limit") val limit: Int,
    @SerialName("total") val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("has_next") val hasNext: Boolean,
    @SerialName("has_previous") val hasPrevious: Boolean
)

@Serializable
data class EntityBase(
    @SerialName("id") val id: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
enum class UserRole {
    @SerialName("admin") ADMIN,
    @SerialName("provider") PROVIDER,
    @SerialName("patient") PATIENT
}

@Serializable
enum class ProgramCategory {
    @SerialName("rehabilitation") REHABILITATION,
    @SerialName("fitness") FITNESS,
    @SerialName("therapy") THERAPY,
    @SerialName("wellness") WELLNESS
}

@Serializable
enum class ProgramDifficulty {
    @SerialName("beginner") BEGINNER,
    @SerialName("intermediate") INTERMEDIATE,
    @SerialName("advanced") ADVANCED
}

@Serializable
data class Address(
    @SerialName("street") val street: String,
    @SerialName("city") val city: String,
    @SerialName("state") val state: String,
    @SerialName("postal_code") val postalCode: String,
    @SerialName("country") val country: String
)

@Serializable
data class EmergencyContact(
    @SerialName("name") val name: String,
    @SerialName("phone") val phone: String,
    @SerialName("relationship") val relationship: String
)

@Serializable
data class MedicalInfo(
    @SerialName("allergies") val allergies: List<String> = emptyList(),
    @SerialName("medications") val medications: List<String> = emptyList(),
    @SerialName("conditions") val conditions: List<String> = emptyList(),
    @SerialName("notes") val notes: String? = null
)