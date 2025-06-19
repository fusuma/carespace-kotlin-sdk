package com.carespace.example.jvm

import com.carespace.sdk.CarespaceClient
import com.carespace.sdk.models.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("=== Carespace Kotlin SDK - JVM Example ===\n")

    // Create client for development environment
    val client = CarespaceClient.forDevelopment()

    try {
        runBasicExample(client)
        println()
        runAdvancedExample(client)
    } catch (e: Exception) {
        println("Error occurred: ${e.message}")
        e.printStackTrace()
    } finally {
        client.close()
    }
}

suspend fun runBasicExample(client: CarespaceClient) {
    println("--- Basic Example ---")

    // Health check
    println("Performing health check...")
    val isHealthy = client.healthCheck()
    println("API Health: ${if (isHealthy) "Healthy" else "Unhealthy"}")

    // Get users
    println("\nGetting users...")
    val users = client.quickGetUsers(limit = 5)
    if (users.success) {
        println("Found ${users.data.size} users:")
        users.data.take(3).forEach { user ->
            println("  - ${user.name} (${user.email}) - Role: ${user.role}")
        }
    } else {
        println("Failed to get users: ${users.error}")
    }

    // Get clients
    println("\nGetting clients...")
    val clients = client.quickGetClients(limit = 5)
    if (clients.success) {
        println("Found ${clients.data.size} clients:")
        clients.data.take(3).forEach { clientItem ->
            println("  - ${clientItem.name} (${clientItem.email}) - Sessions: ${clientItem.totalSessions}")
        }
    } else {
        println("Failed to get clients: ${clients.error}")
    }

    // Get programs
    println("\nGetting rehabilitation programs...")
    val programs = client.quickGetPrograms(limit = 5, category = ProgramCategory.REHABILITATION)
    if (programs.success) {
        println("Found ${programs.data.size} rehabilitation programs:")
        programs.data.take(3).forEach { program ->
            println("  - ${program.name} (${program.difficulty}) - Duration: ${program.duration} min")
        }
    } else {
        println("Failed to get programs: ${programs.error}")
    }

    println("Basic example completed successfully!")
}

suspend fun runAdvancedExample(client: CarespaceClient) {
    println("--- Advanced Example ---")

    try {
        // Create a new user
        println("Creating a new user...")
        val newUser = CreateUserRequest(
            email = "test.user.${System.currentTimeMillis()}@example.com",
            name = "Test User",
            firstName = "Test",
            lastName = "User",
            password = "SecurePassword123!",
            role = UserRole.PATIENT,
            profile = UserProfile(
                phone = "+1-555-0123",
                dateOfBirth = "1990-01-01",
                address = Address(
                    street = "123 Main St",
                    city = "Anytown",
                    state = "CA",
                    postalCode = "12345",
                    country = "USA"
                )
            )
        )

        val createdUserResponse = client.users.createUser(newUser)
        if (createdUserResponse.success && createdUserResponse.data != null) {
            println("Created user: ${createdUserResponse.data.id} - ${createdUserResponse.data.name}")

            // Create a new client
            println("\nCreating a new client...")
            val newClient = CreateClientRequest(
                name = "Jane Doe",
                email = "jane.doe.${System.currentTimeMillis()}@example.com",
                phone = "+1-555-0456",
                dateOfBirth = "1985-05-15",
                gender = "Female",
                medicalInfo = MedicalInfo(
                    allergies = listOf("Peanuts", "Shellfish"),
                    conditions = listOf("Hypertension"),
                    notes = "Patient recovering from knee surgery"
                )
            )

            val createdClientResponse = client.clients.createClient(newClient)
            if (createdClientResponse.success && createdClientResponse.data != null) {
                println("Created client: ${createdClientResponse.data.id} - ${createdClientResponse.data.name}")

                // Create a rehabilitation program
                println("\nCreating a rehabilitation program...")
                val newProgram = CreateProgramRequest(
                    name = "Post-Surgery Knee Rehabilitation",
                    description = "Comprehensive rehabilitation program for post-surgical knee recovery",
                    category = ProgramCategory.REHABILITATION,
                    difficulty = ProgramDifficulty.BEGINNER,
                    duration = 30,
                    isTemplate = false,
                    isPublic = true,
                    tags = listOf("knee", "surgery", "rehabilitation", "beginner")
                )

                val createdProgramResponse = client.programs.createProgram(newProgram)
                if (createdProgramResponse.success && createdProgramResponse.data != null) {
                    println("Created program: ${createdProgramResponse.data.id} - ${createdProgramResponse.data.name}")

                    // Add exercises to the program
                    println("\nAdding exercises to the program...")
                    val exercises = listOf(
                        CreateExerciseRequest(
                            name = "Knee Flexion",
                            description = "Gentle knee bending exercise",
                            instructions = "Slowly bend your knee while lying down, hold for 5 seconds",
                            duration = 60,
                            repetitions = 10,
                            sets = 3,
                            restTime = 30,
                            bodyParts = listOf("Knee", "Quadriceps"),
                            equipment = listOf("Exercise mat"),
                            order = 1
                        ),
                        CreateExerciseRequest(
                            name = "Heel Slides",
                            description = "Slide your heel towards your buttocks",
                            instructions = "While lying down, slide your heel towards your buttocks and back",
                            duration = 45,
                            repetitions = 15,
                            sets = 2,
                            restTime = 20,
                            bodyParts = listOf("Knee", "Hamstring"),
                            equipment = listOf("Exercise mat"),
                            order = 2
                        )
                    )

                    exercises.forEach { exercise ->
                        val exerciseResponse = client.programs.addExerciseToProgram(
                            createdProgramResponse.data.id,
                            exercise
                        )
                        
                        if (exerciseResponse.success && exerciseResponse.data != null) {
                            println("Added exercise: ${exercise.name}")
                        }
                    }

                    // Assign program to client
                    println("\nAssigning program to client...")
                    val assignResponse = client.clients.assignProgram(
                        createdClientResponse.data.id,
                        createdProgramResponse.data.id
                    )
                    
                    if (assignResponse.success) {
                        println("Program assigned successfully!")
                    }

                    // Get client statistics
                    println("\nGetting client stats...")
                    val clientStats = client.clients.getClientStats(createdClientResponse.data.id)
                    if (clientStats.success && clientStats.data != null) {
                        println("Client stats - Total sessions: ${clientStats.data.totalSessions}, " +
                               "Completion rate: ${(clientStats.data.completionRate * 100).toInt()}%")
                    }
                }
            }
        }
    } catch (e: Exception) {
        println("Error in advanced example: ${e.message}")
    }

    println("Advanced example completed!")
}

// Utility functions for demo
fun printSeparator() {
    println("-".repeat(50))
}

fun printHeader(title: String) {
    printSeparator()
    println("  $title")
    printSeparator()
}