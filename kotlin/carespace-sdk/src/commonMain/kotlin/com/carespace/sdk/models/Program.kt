package com.carespace.sdk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Program(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("category") val category: ProgramCategory,
    @SerialName("difficulty") val difficulty: ProgramDifficulty,
    @SerialName("duration") val duration: Int,
    @SerialName("exercises") val exercises: List<Exercise> = emptyList(),
    @SerialName("is_template") val isTemplate: Boolean,
    @SerialName("is_public") val isPublic: Boolean,
    @SerialName("creator_id") val creatorId: String,
    @SerialName("tags") val tags: List<String> = emptyList(),
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class Exercise(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("instructions") val instructions: String? = null,
    @SerialName("duration") val duration: Int? = null,
    @SerialName("repetitions") val repetitions: Int? = null,
    @SerialName("sets") val sets: Int? = null,
    @SerialName("rest_time") val restTime: Int? = null,
    @SerialName("body_parts") val bodyParts: List<String> = emptyList(),
    @SerialName("equipment") val equipment: List<String> = emptyList(),
    @SerialName("media_url") val mediaUrl: String? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("order") val order: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class CreateProgramRequest(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("category") val category: ProgramCategory,
    @SerialName("difficulty") val difficulty: ProgramDifficulty,
    @SerialName("duration") val duration: Int,
    @SerialName("is_template") val isTemplate: Boolean = false,
    @SerialName("is_public") val isPublic: Boolean = false,
    @SerialName("tags") val tags: List<String> = emptyList(),
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null
)

@Serializable
data class UpdateProgramRequest(
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("category") val category: ProgramCategory? = null,
    @SerialName("difficulty") val difficulty: ProgramDifficulty? = null,
    @SerialName("duration") val duration: Int? = null,
    @SerialName("is_template") val isTemplate: Boolean? = null,
    @SerialName("is_public") val isPublic: Boolean? = null,
    @SerialName("tags") val tags: List<String>? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null
)

@Serializable
data class CreateExerciseRequest(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("instructions") val instructions: String? = null,
    @SerialName("duration") val duration: Int? = null,
    @SerialName("repetitions") val repetitions: Int? = null,
    @SerialName("sets") val sets: Int? = null,
    @SerialName("rest_time") val restTime: Int? = null,
    @SerialName("body_parts") val bodyParts: List<String> = emptyList(),
    @SerialName("equipment") val equipment: List<String> = emptyList(),
    @SerialName("media_url") val mediaUrl: String? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("order") val order: Int
)