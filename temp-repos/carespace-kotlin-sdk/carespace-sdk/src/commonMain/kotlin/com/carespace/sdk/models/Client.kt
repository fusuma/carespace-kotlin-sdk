package com.carespace.sdk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Client(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("phone") val phone: String? = null,
    @SerialName("date_of_birth") val dateOfBirth: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("address") val address: Address? = null,
    @SerialName("emergency_contact") val emergencyContact: EmergencyContact? = null,
    @SerialName("medical_info") val medicalInfo: MedicalInfo? = null,
    @SerialName("assigned_programs") val assignedPrograms: List<String> = emptyList(),
    @SerialName("provider_id") val providerId: String? = null,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("last_session") val lastSession: String? = null,
    @SerialName("total_sessions") val totalSessions: Int,
    @SerialName("notes") val notes: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class CreateClientRequest(
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("phone") val phone: String? = null,
    @SerialName("date_of_birth") val dateOfBirth: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("address") val address: Address? = null,
    @SerialName("emergency_contact") val emergencyContact: EmergencyContact? = null,
    @SerialName("medical_info") val medicalInfo: MedicalInfo? = null,
    @SerialName("provider_id") val providerId: String? = null,
    @SerialName("notes") val notes: String? = null
)

@Serializable
data class UpdateClientRequest(
    @SerialName("name") val name: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("phone") val phone: String? = null,
    @SerialName("date_of_birth") val dateOfBirth: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("address") val address: Address? = null,
    @SerialName("emergency_contact") val emergencyContact: EmergencyContact? = null,
    @SerialName("medical_info") val medicalInfo: MedicalInfo? = null,
    @SerialName("is_active") val isActive: Boolean? = null,
    @SerialName("notes") val notes: String? = null
)

@Serializable
data class ClientStats(
    @SerialName("total_sessions") val totalSessions: Int,
    @SerialName("total_exercises") val totalExercises: Int,
    @SerialName("total_time") val totalTime: Int,
    @SerialName("last_session") val lastSession: String? = null,
    @SerialName("average_score") val averageScore: Double? = null,
    @SerialName("completion_rate") val completionRate: Double,
    @SerialName("progress_trend") val progressTrend: String
)