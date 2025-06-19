package com.carespace.sdk.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.*

class ModelsTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun testUserSerialization() {
        val user = User(
            id = "user-123",
            email = "test@example.com",
            name = "Test User",
            firstName = "Test",
            lastName = "User",
            role = UserRole.PATIENT,
            isActive = true,
            createdAt = "2023-01-01T00:00:00Z",
            updatedAt = "2023-01-01T00:00:00Z"
        )

        val jsonString = json.encodeToString(user)
        val deserializedUser = json.decodeFromString<User>(jsonString)

        assertEquals(user, deserializedUser)
        assertTrue(jsonString.contains("\"role\":\"patient\""))
    }

    @Test
    fun testClientSerialization() {
        val client = Client(
            id = "client-123",
            name = "John Doe",
            email = "john@example.com",
            phone = "+1-555-0123",
            isActive = true,
            totalSessions = 5,
            createdAt = "2023-01-01T00:00:00Z",
            updatedAt = "2023-01-01T00:00:00Z"
        )

        val jsonString = json.encodeToString(client)
        val deserializedClient = json.decodeFromString<Client>(jsonString)

        assertEquals(client, deserializedClient)
        assertTrue(jsonString.contains("\"total_sessions\":5"))
    }

    @Test
    fun testProgramSerialization() {
        val program = Program(
            id = "program-123",
            name = "Test Program",
            description = "A test program",
            category = ProgramCategory.REHABILITATION,
            difficulty = ProgramDifficulty.BEGINNER,
            duration = 30,
            isTemplate = false,
            isPublic = true,
            creatorId = "user-123",
            createdAt = "2023-01-01T00:00:00Z",
            updatedAt = "2023-01-01T00:00:00Z"
        )

        val jsonString = json.encodeToString(program)
        val deserializedProgram = json.decodeFromString<Program>(jsonString)

        assertEquals(program, deserializedProgram)
        assertTrue(jsonString.contains("\"category\":\"rehabilitation\""))
        assertTrue(jsonString.contains("\"difficulty\":\"beginner\""))
    }

    @Test
    fun testApiResponseSerialization() {
        val apiResponse = ApiResponse(
            success = true,
            data = "test data",
            message = "Success"
        )

        val jsonString = json.encodeToString(apiResponse)
        val deserializedResponse = json.decodeFromString<ApiResponse<String>>(jsonString)

        assertEquals(apiResponse, deserializedResponse)
        assertTrue(jsonString.contains("\"success\":true"))
    }

    @Test
    fun testPaginatedResponseSerialization() {
        val users = listOf(
            User(
                id = "user-1",
                email = "user1@example.com",
                name = "User One",
                firstName = "User",
                lastName = "One",
                role = UserRole.PATIENT,
                isActive = true,
                createdAt = "2023-01-01T00:00:00Z",
                updatedAt = "2023-01-01T00:00:00Z"
            )
        )

        val paginatedResponse = PaginatedResponse(
            success = true,
            data = users,
            page = 1,
            limit = 20,
            total = 1,
            totalPages = 1,
            hasNext = false,
            hasPrevious = false
        )

        val jsonString = json.encodeToString(paginatedResponse)
        val deserializedResponse = json.decodeFromString<PaginatedResponse<User>>(jsonString)

        assertEquals(paginatedResponse, deserializedResponse)
        assertTrue(jsonString.contains("\"has_next\":false"))
    }

    @Test
    fun testLoginRequestSerialization() {
        val loginRequest = LoginRequest(
            email = "test@example.com",
            password = "password123"
        )

        val jsonString = json.encodeToString(loginRequest)
        val deserializedRequest = json.decodeFromString<LoginRequest>(jsonString)

        assertEquals(loginRequest, deserializedRequest)
        assertTrue(jsonString.contains("\"email\":\"test@example.com\""))
    }

    @Test
    fun testCreateClientRequestSerialization() {
        val createRequest = CreateClientRequest(
            name = "Jane Doe",
            email = "jane@example.com",
            phone = "+1-555-0456",
            medicalInfo = MedicalInfo(
                allergies = listOf("Peanuts", "Shellfish"),
                conditions = listOf("Hypertension"),
                notes = "Patient notes"
            )
        )

        val jsonString = json.encodeToString(createRequest)
        val deserializedRequest = json.decodeFromString<CreateClientRequest>(jsonString)

        assertEquals(createRequest, deserializedRequest)
        assertTrue(jsonString.contains("\"allergies\":[\"Peanuts\",\"Shellfish\"]"))
    }

    @Test
    fun testEnumValues() {
        assertEquals("admin", UserRole.ADMIN.name.lowercase())
        assertEquals("provider", UserRole.PROVIDER.name.lowercase())
        assertEquals("patient", UserRole.PATIENT.name.lowercase())

        assertEquals("rehabilitation", ProgramCategory.REHABILITATION.name.lowercase())
        assertEquals("fitness", ProgramCategory.FITNESS.name.lowercase())
        assertEquals("therapy", ProgramCategory.THERAPY.name.lowercase())
        assertEquals("wellness", ProgramCategory.WELLNESS.name.lowercase())

        assertEquals("beginner", ProgramDifficulty.BEGINNER.name.lowercase())
        assertEquals("intermediate", ProgramDifficulty.INTERMEDIATE.name.lowercase())
        assertEquals("advanced", ProgramDifficulty.ADVANCED.name.lowercase())
    }

    @Test
    fun testAddressSerialization() {
        val address = Address(
            street = "123 Main St",
            city = "Anytown",
            state = "CA",
            postalCode = "12345",
            country = "USA"
        )

        val jsonString = json.encodeToString(address)
        val deserializedAddress = json.decodeFromString<Address>(jsonString)

        assertEquals(address, deserializedAddress)
        assertTrue(jsonString.contains("\"postal_code\":\"12345\""))
    }
}