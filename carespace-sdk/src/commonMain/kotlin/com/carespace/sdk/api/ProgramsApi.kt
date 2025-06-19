package com.carespace.sdk.api

import com.carespace.sdk.http.CarespaceHttpClient
import com.carespace.sdk.models.*

/**
 * Programs API interface
 */
interface ProgramsApi {
    suspend fun getPrograms(
        page: Int = 1,
        limit: Int = 20,
        search: String? = null,
        category: ProgramCategory? = null,
        difficulty: ProgramDifficulty? = null,
        isTemplate: Boolean? = null,
        isPublic: Boolean? = null,
        creatorId: String? = null
    ): PaginatedResponse<Program>

    suspend fun getProgram(programId: String): ApiResponse<Program>
    suspend fun createProgram(request: CreateProgramRequest): ApiResponse<Program>
    suspend fun updateProgram(programId: String, request: UpdateProgramRequest): ApiResponse<Program>
    suspend fun deleteProgram(programId: String)
    suspend fun getProgramExercises(programId: String, page: Int = 1, limit: Int = 20): PaginatedResponse<Exercise>
    suspend fun addExerciseToProgram(programId: String, request: CreateExerciseRequest): ApiResponse<Exercise>
    suspend fun updateProgramExercise(programId: String, exerciseId: String, request: CreateExerciseRequest): ApiResponse<Exercise>
    suspend fun removeExerciseFromProgram(programId: String, exerciseId: String)
    suspend fun duplicateProgram(programId: String, newName: String? = null): ApiResponse<Program>
    suspend fun createProgramFromTemplate(templateId: String, name: String? = null): ApiResponse<Program>
}

/**
 * Programs API implementation
 */
class ProgramsApiImpl(private val httpClient: CarespaceHttpClient) : ProgramsApi {

    override suspend fun getPrograms(
        page: Int,
        limit: Int,
        search: String?,
        category: ProgramCategory?,
        difficulty: ProgramDifficulty?,
        isTemplate: Boolean?,
        isPublic: Boolean?,
        creatorId: String?
    ): PaginatedResponse<Program> {
        val parameters = buildMap {
            put("page", page)
            put("limit", limit)
            search?.let { put("search", it) }
            category?.let { put("category", it.name.lowercase()) }
            difficulty?.let { put("difficulty", it.name.lowercase()) }
            isTemplate?.let { put("is_template", it) }
            isPublic?.let { put("is_public", it) }
            creatorId?.let { put("creator_id", it) }
        }
        return httpClient.get("/programs", parameters)
    }

    override suspend fun getProgram(programId: String): ApiResponse<Program> {
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        return httpClient.get("/programs/$programId")
    }

    override suspend fun createProgram(request: CreateProgramRequest): ApiResponse<Program> {
        return httpClient.post("/programs", request)
    }

    override suspend fun updateProgram(programId: String, request: UpdateProgramRequest): ApiResponse<Program> {
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        return httpClient.put("/programs/$programId", request)
    }

    override suspend fun deleteProgram(programId: String) {
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        httpClient.delete("/programs/$programId")
    }

    override suspend fun getProgramExercises(programId: String, page: Int, limit: Int): PaginatedResponse<Exercise> {
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        val parameters = mapOf("page" to page, "limit" to limit)
        return httpClient.get("/programs/$programId/exercises", parameters)
    }

    override suspend fun addExerciseToProgram(programId: String, request: CreateExerciseRequest): ApiResponse<Exercise> {
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        return httpClient.post("/programs/$programId/exercises", request)
    }

    override suspend fun updateProgramExercise(
        programId: String,
        exerciseId: String,
        request: CreateExerciseRequest
    ): ApiResponse<Exercise> {
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        require(exerciseId.isNotBlank()) { "Exercise ID cannot be blank" }
        return httpClient.put("/programs/$programId/exercises/$exerciseId", request)
    }

    override suspend fun removeExerciseFromProgram(programId: String, exerciseId: String) {
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        require(exerciseId.isNotBlank()) { "Exercise ID cannot be blank" }
        httpClient.delete("/programs/$programId/exercises/$exerciseId")
    }

    override suspend fun duplicateProgram(programId: String, newName: String?): ApiResponse<Program> {
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        val request = newName?.let { mapOf("name" to it) }
        return httpClient.post("/programs/$programId/duplicate", request)
    }

    override suspend fun createProgramFromTemplate(templateId: String, name: String?): ApiResponse<Program> {
        require(templateId.isNotBlank()) { "Template ID cannot be blank" }
        val request = name?.let { mapOf("name" to it) }
        return httpClient.post("/programs/from-template/$templateId", request)
    }
}