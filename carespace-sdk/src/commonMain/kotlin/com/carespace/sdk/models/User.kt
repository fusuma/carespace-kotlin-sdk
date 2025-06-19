package com.carespace.sdk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String,
    @SerialName("name") val name: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("role") val role: UserRole,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("last_login") val lastLogin: String? = null,
    @SerialName("profile") val profile: UserProfile? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class UserProfile(
    @SerialName("phone") val phone: String? = null,
    @SerialName("date_of_birth") val dateOfBirth: String? = null,
    @SerialName("address") val address: Address? = null,
    @SerialName("emergency_contact") val emergencyContact: EmergencyContact? = null,
    @SerialName("medical_info") val medicalInfo: MedicalInfo? = null
)

@Serializable
data class CreateUserRequest(
    @SerialName("email") val email: String,
    @SerialName("name") val name: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("password") val password: String,
    @SerialName("role") val role: UserRole,
    @SerialName("profile") val profile: UserProfile? = null
)

@Serializable
data class UpdateUserRequest(
    @SerialName("name") val name: String? = null,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("is_active") val isActive: Boolean? = null,
    @SerialName("profile") val profile: UserProfile? = null
)