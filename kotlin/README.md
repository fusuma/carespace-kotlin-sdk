# Carespace Kotlin SDK

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org/)
[![Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-orange.svg)](https://kotlinlang.org/docs/multiplatform.html)
[![Android](https://img.shields.io/badge/Android-24%2B-green.svg)](https://developer.android.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Official Kotlin SDK for the Carespace API - Build powerful healthcare and rehabilitation applications with modern Kotlin multiplatform support.

## Features

- 🚀 **Kotlin Multiplatform** - Supports Android, JVM, and more
- ⚡ **Coroutines** - Modern async programming with suspend functions
- 🔒 **Type Safety** - Fully type-safe with data classes and sealed classes
- 📦 **Kotlinx.serialization** - Fast and efficient JSON serialization
- 🌐 **Ktor Client** - Modern HTTP client with retry logic
- 📱 **Android Optimized** - Lifecycle-aware components and secure storage
- 🧪 **Well Tested** - Comprehensive unit tests with MockK
- 📚 **Rich Documentation** - KDoc comments and examples

## Installation

### Gradle (Kotlin DSL)

Add to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.carespace:sdk-kotlin:1.0.0")
}
```

### Android

For Android projects, add the Android-specific dependency:

```kotlin
dependencies {
    implementation("com.carespace:sdk-kotlin-android:1.0.0")
}
```

### Maven

```xml
<dependency>
    <groupId>com.carespace</groupId>
    <artifactId>sdk-kotlin</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

### Basic Usage (JVM/Android)

```kotlin
import com.carespace.sdk.CarespaceClient
import com.carespace.sdk.models.*

// Create client
val client = CarespaceClient.forDevelopment("your-api-key")

// Authenticate
val loginResponse = client.loginAndSetToken("user@example.com", "password")
println("Logged in as: ${loginResponse.user.name}")

// Get users
val users = client.quickGetUsers(limit = 10)
println("Found ${users.data.size} users")

// Create a client
val newClient = CreateClientRequest(
    name = "John Doe",
    email = "john@example.com",
    phone = "+1-555-0123"
)
val createdClient = client.clients.createClient(newClient)
println("Created client: ${createdClient.data?.id}")

// Clean up
client.close()
```

### Android with Lifecycle

```kotlin
import com.carespace.sdk.android.CarespaceAndroidClient
import com.carespace.sdk.android.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create Android client
        val client = CarespaceAndroidClient.forDevelopment(this, "your-api-key")
        
        // Use with lifecycle
        client.executeWithLifecycle(
            lifecycleOwner = this,
            onSuccess = { users: PaginatedResponse<User> ->
                // Handle success
            },
            onError = { error ->
                // Handle error
            }
        ) {
            client.users.getUsers()
        }
    }
}
```

### Compose UI with ViewModels

```kotlin
@Composable
fun UsersScreen(
    usersViewModel: UsersViewModel = koinViewModel()
) {
    val users by usersViewModel.users.collectAsState()
    val isLoading by usersViewModel.isLoading.collectAsState()
    
    LaunchedEffect(Unit) {
        usersViewModel.getUsers()
    }
    
    when {
        isLoading -> CircularProgressIndicator()
        else -> {
            LazyColumn {
                items(users) { user ->
                    UserItem(user = user)
                }
            }
        }
    }
}
```

## Configuration

### Environment Configurations

```kotlin
// Development
val devClient = CarespaceClient.forDevelopment("api-key")

// Production
val prodClient = CarespaceClient.forProduction("api-key")

// Staging
val stagingClient = CarespaceClient.forStaging("api-key")

// Custom configuration
val customConfig = CarespaceConfiguration(
    baseUrl = "https://custom-api.carespace.ai",
    apiKey = "your-api-key",
    timeout = 60.seconds,
    maxRetryAttempts = 5,
    enableLogging = true
)
val customClient = CarespaceClient.withConfiguration(customConfig)
```

### Android Dependency Injection

```kotlin
// Koin module
val appModule = module {
    single { CarespaceAndroidClient.forDevelopment(get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { UsersViewModel(get()) }
    viewModel { ClientsViewModel(get()) }
    viewModel { ProgramsViewModel(get()) }
}

// Application class
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}
```

## API Reference

### Authentication

```kotlin
// Login
val loginResponse = client.auth.login("user@example.com", "password")

// Refresh token
val refreshResponse = client.auth.refreshToken("refresh-token")

// Change password
client.auth.changePassword("currentPassword", "newPassword")

// Logout
client.auth.logout()
```

### Users

```kotlin
// Get users with pagination and filtering
val users = client.users.getUsers(
    page = 1,
    limit = 20,
    search = "john",
    role = UserRole.PATIENT,
    isActive = true
)

// Get specific user
val user = client.users.getUser("user-id")

// Create user
val newUser = CreateUserRequest(
    email = "user@example.com",
    name = "John Doe",
    firstName = "John",
    lastName = "Doe",
    password = "SecurePassword123!",
    role = UserRole.PATIENT
)
val createdUser = client.users.createUser(newUser)

// Update user
val updateUser = UpdateUserRequest(name = "Jane Doe")
client.users.updateUser("user-id", updateUser)
```

### Clients

```kotlin
// Get clients
val clients = client.clients.getClients(
    page = 1,
    limit = 20,
    search = "doe",
    providerId = "provider-id"
)

// Create client with medical info
val newClient = CreateClientRequest(
    name = "Jane Doe",
    email = "jane@example.com",
    phone = "+1-555-0456",
    dateOfBirth = "1990-01-01",
    medicalInfo = MedicalInfo(
        allergies = listOf("Peanuts"),
        conditions = listOf("Hypertension"),
        notes = "Patient notes"
    )
)
val createdClient = client.clients.createClient(newClient)

// Get client statistics
val stats = client.clients.getClientStats("client-id")
println("Total sessions: ${stats.data?.totalSessions}")

// Assign program to client
client.clients.assignProgram("client-id", "program-id")
```

### Programs

```kotlin
// Get programs with filtering
val programs = client.programs.getPrograms(
    page = 1,
    limit = 20,
    category = ProgramCategory.REHABILITATION,
    difficulty = ProgramDifficulty.BEGINNER
)

// Create program
val newProgram = CreateProgramRequest(
    name = "Post-Surgery Knee Rehabilitation",
    description = "Comprehensive knee rehabilitation program",
    category = ProgramCategory.REHABILITATION,
    difficulty = ProgramDifficulty.BEGINNER,
    duration = 30,
    isPublic = true
)
val createdProgram = client.programs.createProgram(newProgram)

// Add exercise to program
val exercise = CreateExerciseRequest(
    name = "Knee Flexion",
    description = "Gentle knee bending exercise",
    duration = 60,
    repetitions = 10,
    sets = 3,
    order = 1
)
client.programs.addExerciseToProgram("program-id", exercise)

// Get program exercises
val exercises = client.programs.getProgramExercises("program-id")
```

## Error Handling

The SDK provides specific exception types for different error scenarios:

```kotlin
try {
    val user = client.users.getUser("invalid-id")
} catch (e: CarespaceNotFoundException) {
    println("User not found")
} catch (e: CarespaceAuthenticationException) {
    println("Authentication failed")
} catch (e: CarespaceValidationException) {
    println("Validation failed: ${e.errorDetails}")
} catch (e: CarespaceRateLimitException) {
    println("Rate limit exceeded. Retry after: ${e.retryAfterSeconds}s")
} catch (e: CarespaceException) {
    println("API error: ${e.message} (Status: ${e.statusCode})")
}
```

## Android Features

### Secure Storage

```kotlin
// The Android client automatically handles secure token storage
val client = CarespaceAndroidClient.forDevelopment(context)

// Cache authentication token
client.cacheApiKey("your-api-key")

// Get cached token
val cachedKey = client.getCachedApiKey()

// Clear cached token
client.clearCachedApiKey()
```

### Network Connectivity

```kotlin
// Check network availability
if (client.isNetworkAvailable()) {
    // Proceed with API calls
    val users = client.users.getUsers()
} else {
    // Handle offline state
    showOfflineMessage()
}
```

### ViewModels

```kotlin
class MyViewModel(private val carespace: CarespaceClient) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()
    
    fun loadUsers() {
        viewModelScope.launch {
            try {
                val response = carespace.users.getUsers()
                if (response.success) {
                    _users.value = response.data
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
```

## Examples

### JVM Console Application

Check out the [JVM example](./examples/jvm-app/) for a complete console application demonstrating:
- Basic API operations
- Error handling
- User, client, and program management

Run the example:
```bash
cd examples/jvm-app
../gradlew run
```

### Android Application

The [Android example](./examples/android-app/) demonstrates:
- Jetpack Compose UI
- MVVM architecture with ViewModels
- Dependency injection with Koin
- Lifecycle-aware API calls
- Secure token storage

## Testing

```bash
# Run all tests
./gradlew test

# Run specific platform tests
./gradlew jvmTest
./gradlew testDebugUnitTest  # Android

# Run with coverage
./gradlew testCoverageReport
```

## Building from Source

```bash
# Clone the repository
git clone https://github.com/carespace/sdk-monorepo.git
cd carespace-sdk/kotlin

# Build the SDK
./gradlew build

# Publish to local repository
./gradlew publishToMavenLocal

# Run examples
./gradlew :examples:jvm-app:run
```

## Supported Platforms

| Platform | Status | Minimum Version |
|----------|--------|----------------|
| Android | ✅ Supported | API 24+ |
| JVM | ✅ Supported | Java 11+ |
| iOS | 🚧 Planned | iOS 13+ |
| JavaScript | 🚧 Planned | ES2018+ |

## Requirements

- Kotlin 1.9.20+
- Android API 24+ (Android 7.0)
- Java 11+ (for JVM targets)
- Gradle 8.0+

## Dependencies

- **Kotlin Multiplatform** - Core multiplatform support
- **Kotlinx.coroutines** - Async programming
- **Kotlinx.serialization** - JSON serialization
- **Ktor Client** - HTTP networking
- **Koin** - Dependency injection (optional)

### Android-specific
- **AndroidX Lifecycle** - Lifecycle-aware components
- **EncryptedSharedPreferences** - Secure storage

## Contributing

We welcome contributions! Please see our [Contributing Guide](../CONTRIBUTING.md) for details.

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## Migration Guide

### From Java/REST API

```kotlin
// Before (Java/REST)
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.getForEntity(
    "https://api.carespace.ai/users", String.class);

// After (Kotlin SDK)
val client = CarespaceClient.forProduction("api-key")
val users = client.users.getUsers()
```

### Adding to Existing Android Project

1. Add the dependency to your `build.gradle.kts`
2. Initialize the client in your Application class
3. Set up dependency injection (optional)
4. Use ViewModels for lifecycle-aware operations

## License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.

## Support

- 📧 Email: [support@carespace.ai](mailto:support@carespace.ai)
- 💬 [Discord Community](https://discord.gg/carespace)
- 🐛 [Report Issues](https://github.com/carespace/sdk-monorepo/issues)
- 📖 [API Documentation](https://docs.carespace.ai/api)

## Changelog

### Version 1.0.0
- Initial release with Kotlin Multiplatform support
- Complete API coverage (Auth, Users, Clients, Programs)
- Android-specific features and ViewModels
- Comprehensive testing suite
- Example applications

---

<p align="center">
  <strong>Built with ❤️ by the Carespace Team</strong><br>
  <a href="https://carespace.ai">🌐 Website</a> •
  <a href="https://docs.carespace.ai">📖 Docs</a> •
  <a href="https://github.com/carespace">🔗 GitHub</a>
</p>