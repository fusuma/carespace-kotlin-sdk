package com.carespace.sdk.api

import com.carespace.sdk.http.CarespaceHttpClient
import com.carespace.sdk.models.*

/**
 * Clients API interface
 */
interface ClientsApi {
    suspend fun getClients(
        page: Int = 1,
        limit: Int = 20,
        search: String? = null,
        providerId: String? = null,
        isActive: Boolean? = null
    ): PaginatedResponse<Client>

    suspend fun getClient(clientId: String): ApiResponse<Client>
    suspend fun createClient(request: CreateClientRequest): ApiResponse<Client>
    suspend fun updateClient(clientId: String, request: UpdateClientRequest): ApiResponse<Client>
    suspend fun deleteClient(clientId: String)
    suspend fun getClientStats(clientId: String): ApiResponse<ClientStats>
    suspend fun getClientPrograms(clientId: String, page: Int = 1, limit: Int = 20): PaginatedResponse<Program>
    suspend fun assignProgram(clientId: String, programId: String): ApiResponse<Unit>
    suspend fun unassignProgram(clientId: String, programId: String): ApiResponse<Unit>
    suspend fun deactivateClient(clientId: String): ApiResponse<Unit>
    suspend fun activateClient(clientId: String): ApiResponse<Unit>
}

/**
 * Clients API implementation
 */
class ClientsApiImpl(private val httpClient: CarespaceHttpClient) : ClientsApi {

    override suspend fun getClients(
        page: Int,
        limit: Int,
        search: String?,
        providerId: String?,
        isActive: Boolean?
    ): PaginatedResponse<Client> {
        val parameters = buildMap {
            put("page", page)
            put("limit", limit)
            search?.let { put("search", it) }
            providerId?.let { put("provider_id", it) }
            isActive?.let { put("is_active", it) }
        }
        return httpClient.get("/clients", parameters)
    }

    override suspend fun getClient(clientId: String): ApiResponse<Client> {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        return httpClient.get("/clients/$clientId")
    }

    override suspend fun createClient(request: CreateClientRequest): ApiResponse<Client> {
        return httpClient.post("/clients", request)
    }

    override suspend fun updateClient(clientId: String, request: UpdateClientRequest): ApiResponse<Client> {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        return httpClient.put("/clients/$clientId", request)
    }

    override suspend fun deleteClient(clientId: String) {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        httpClient.delete("/clients/$clientId")
    }

    override suspend fun getClientStats(clientId: String): ApiResponse<ClientStats> {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        return httpClient.get("/clients/$clientId/stats")
    }

    override suspend fun getClientPrograms(clientId: String, page: Int, limit: Int): PaginatedResponse<Program> {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        val parameters = mapOf("page" to page, "limit" to limit)
        return httpClient.get("/clients/$clientId/programs", parameters)
    }

    override suspend fun assignProgram(clientId: String, programId: String): ApiResponse<Unit> {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        val request = mapOf("program_id" to programId)
        return httpClient.post("/clients/$clientId/programs", request)
    }

    override suspend fun unassignProgram(clientId: String, programId: String): ApiResponse<Unit> {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        require(programId.isNotBlank()) { "Program ID cannot be blank" }
        return httpClient.delete("/clients/$clientId/programs/$programId")
    }

    override suspend fun deactivateClient(clientId: String): ApiResponse<Unit> {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        return httpClient.post("/clients/$clientId/deactivate")
    }

    override suspend fun activateClient(clientId: String): ApiResponse<Unit> {
        require(clientId.isNotBlank()) { "Client ID cannot be blank" }
        return httpClient.post("/clients/$clientId/activate")
    }
}