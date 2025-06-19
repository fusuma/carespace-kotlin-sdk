# Carespace JVM Console Example

This JVM console application demonstrates how to use the Carespace Kotlin SDK in server-side applications, background services, or command-line tools.

## Features Demonstrated

- **SDK Initialization**: Setting up the Carespace client for different environments
- **Basic Operations**: Health checks, data retrieval with pagination
- **Advanced Workflows**: Creating users, clients, and programs with relationships
- **Error Handling**: Graceful error handling and recovery patterns
- **Resource Management**: Proper client lifecycle and cleanup
- **Batch Operations**: Working with multiple API endpoints efficiently

## Application Overview

### Structure

```
jvm-app/
├── src/main/kotlin/com/carespace/example/jvm/
│   └── Main.kt                      # Main application with examples
├── build.gradle.kts                 # Build configuration
└── README.md                        # This documentation
```

### Core Functionality

The application runs two main scenarios:

1. **Basic Example**: Simple API operations and data retrieval
2. **Advanced Example**: Complex workflows with entity creation and relationships

## Getting Started

### Prerequisites

- **Java 11+** (for JVM runtime)
- **Kotlin 1.9.20+**
- **Gradle 8.0+**
- **Network access** to Carespace API

### Running the Application

#### From Command Line

```bash
# Navigate to the project root
cd carespace-kotlin-sdk

# Run the JVM example
./gradlew :examples:jvm-app:run

# Or run with Gradle wrapper from examples directory
cd examples/jvm-app
../../gradlew run
```

#### From IDE

1. Open the project in IntelliJ IDEA or your preferred IDE
2. Navigate to `examples/jvm-app/src/main/kotlin/com/carespace/example/jvm/Main.kt`
3. Run the `main` function

### Configuration

The example uses the development environment by default:

```kotlin
// Main.kt
val client = CarespaceClient.forDevelopment()
```

To use different environments:

```kotlin
// Production
val client = CarespaceClient.forProduction("your-api-key")

// Staging  
val client = CarespaceClient.forStaging("your-api-key")

// Custom configuration
val config = CarespaceConfiguration(
    baseUrl = "https://custom-api.carespace.ai",
    apiKey = "your-api-key",
    timeout = 60.seconds,
    enableLogging = true
)
val client = CarespaceClient.withConfiguration(config)
```

## Example Walkthrough

### Basic Example Flow

#### 1. Health Check
```kotlin
val isHealthy = client.healthCheck()
println("API Health: ${if (isHealthy) "Healthy" else "Unhealthy"}")
```

#### 2. Retrieve Users
```kotlin
val users = client.quickGetUsers(limit = 5)
if (users.success) {
    users.data.forEach { user ->
        println("  - ${user.name} (${user.email}) - Role: ${user.role}")
    }
}
```

#### 3. Retrieve Clients
```kotlin
val clients = client.quickGetClients(limit = 5)
if (clients.success) {
    clients.data.forEach { client ->
        println("  - ${client.name} - Sessions: ${client.totalSessions}")
    }
}
```

#### 4. Retrieve Programs
```kotlin
val programs = client.quickGetPrograms(
    limit = 5, 
    category = ProgramCategory.REHABILITATION
)
```

### Advanced Example Flow

#### 1. Create a User
```kotlin
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

val createdUser = client.users.createUser(newUser)
```

#### 2. Create a Client with Medical Information
```kotlin
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

val createdClient = client.clients.createClient(newClient)
```

#### 3. Create a Rehabilitation Program
```kotlin
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

val createdProgram = client.programs.createProgram(newProgram)
```

#### 4. Add Exercises to Program
```kotlin
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
    client.programs.addExerciseToProgram(programId, exercise)
}
```

#### 5. Assign Program to Client
```kotlin
val assignResponse = client.clients.assignProgram(clientId, programId)
```

#### 6. Get Client Statistics
```kotlin
val clientStats = client.clients.getClientStats(clientId)
println("Total sessions: ${clientStats.data?.totalSessions}")
println("Completion rate: ${(clientStats.data?.completionRate * 100).toInt()}%")
```

## Error Handling Patterns

### Basic Error Handling
```kotlin
try {
    val users = client.users.getUsers()
    if (users.success) {
        // Process users
    } else {
        println("Failed to get users: ${users.error}")
    }
} catch (e: CarespaceException) {
    println("API error: ${e.message} (Status: ${e.statusCode})")
}
```

### Specific Exception Types
```kotlin
try {
    val user = client.users.getUser("invalid-id")
} catch (e: CarespaceNotFoundException) {
    println("User not found")
} catch (e: CarespaceAuthenticationException) {
    println("Authentication failed - check API key")
} catch (e: CarespaceValidationException) {
    println("Validation failed: ${e.errorDetails}")
} catch (e: CarespaceRateLimitException) {
    println("Rate limit exceeded. Retry after: ${e.retryAfterSeconds}s")
    delay(e.retryAfterSeconds.seconds)
    // Retry operation
}
```

### Resource Management
```kotlin
val client = CarespaceClient.forDevelopment()
try {
    // Perform operations
    runBasicExample(client)
    runAdvancedExample(client)
} catch (e: Exception) {
    println("Error occurred: ${e.message}")
    e.printStackTrace()
} finally {
    // Always close the client to free resources
    client.close()
}
```

## Use Cases

### Script Automation
Perfect for:
- Data migration scripts
- Batch processing operations
- Scheduled data synchronization
- Administrative tasks

### Backend Services
Ideal for:
- Microservices integration
- Background job processing
- API gateways and proxies
- Data analysis pipelines

### Testing and Development
Great for:
- API testing and validation
- Development environment setup
- Data seeding for testing
- Performance benchmarking

## Dependencies

```kotlin
// build.gradle.kts
dependencies {
    implementation(project(":carespace-sdk"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    
    // Optional: Logging
    implementation("ch.qos.logback:logback-classic:1.4.11")
    
    // Optional: CLI argument parsing
    implementation("com.github.ajalt.clikt:clikt:4.2.1")
}
```

## Extending the Example

### Adding Command Line Arguments

```kotlin
// Using Clikt library
class CarespaceCliApp : CliktCommand() {
    private val environment by option(help = "Environment (dev/staging/prod)")
        .default("dev")
    private val apiKey by option(help = "API key for authentication")
    private val operation by argument(help = "Operation to perform")

    override fun run() {
        val client = when (environment) {
            "prod" -> CarespaceClient.forProduction(apiKey ?: "")
            "staging" -> CarespaceClient.forStaging(apiKey ?: "")
            else -> CarespaceClient.forDevelopment()
        }
        
        // Execute based on operation argument
        when (operation) {
            "users" -> runUsersExample(client)
            "clients" -> runClientsExample(client)
            "programs" -> runProgramsExample(client)
        }
    }
}
```

### Adding Configuration Files

```kotlin
// Using properties file
val config = Properties().apply {
    load(FileInputStream("carespace.properties"))
}

val client = CarespaceClient.withConfiguration(
    CarespaceConfiguration(
        baseUrl = config.getProperty("carespace.baseUrl"),
        apiKey = config.getProperty("carespace.apiKey"),
        timeout = config.getProperty("carespace.timeout", "30").toLong().seconds
    )
)
```

### Batch Operations

```kotlin
suspend fun batchCreateClients(clients: List<CreateClientRequest>) {
    val results = clients.map { clientRequest ->
        async {
            try {
                client.clients.createClient(clientRequest)
            } catch (e: Exception) {
                println("Failed to create client ${clientRequest.name}: ${e.message}")
                null
            }
        }
    }.awaitAll()
    
    val successful = results.filterNotNull().size
    println("Successfully created $successful out of ${clients.size} clients")
}
```

## Testing

### Running Tests
```bash
./gradlew :examples:jvm-app:test
```

### Sample Test Structure
```kotlin
class MainTest {
    @Test
    fun `basic example should complete successfully`() = runTest {
        val mockClient = mockCarespaceClient()
        // Test basic example logic
    }
    
    @Test
    fun `advanced example should handle errors gracefully`() = runTest {
        // Test error scenarios
    }
}
```

## Performance Considerations

- **Connection Pooling**: The SDK automatically manages HTTP connections
- **Timeouts**: Configure appropriate timeouts for your use case
- **Rate Limiting**: Respect API rate limits with exponential backoff
- **Memory Usage**: Close clients to free resources when done
- **Concurrency**: Use coroutines for concurrent operations

## Troubleshooting

### Common Issues

**Issue**: Connection timeout
**Solution**: Increase timeout in configuration or check network connectivity

**Issue**: Authentication failed
**Solution**: Verify API key and environment configuration

**Issue**: Rate limit exceeded
**Solution**: Implement retry logic with exponential backoff

**Issue**: Out of memory
**Solution**: Process data in smaller batches, close unused clients

### Debug Logging

Enable detailed logging:
```kotlin
val config = CarespaceConfiguration(
    baseUrl = "https://api.carespace.ai",
    enableLogging = true,
    logLevel = LogLevel.DEBUG
)
```

## Contributing

To contribute to this example:

1. Follow the main [Contributing Guidelines](../../CONTRIBUTING.md)
2. Add tests for new functionality
3. Update documentation for significant changes
4. Ensure compatibility with different JVM versions

---

This JVM example provides a solid foundation for integrating the Carespace SDK into server-side applications and automation scripts. 🖥️⚕️