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

The SDK provides comprehensive error handling with specific exception types for different scenarios:

### Exception Types

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

### Network and Timeout Handling

```kotlin
class NetworkAwareRepository(private val client: CarespaceClient) {
    
    suspend fun getUsersWithRetry(maxRetries: Int = 3): ApiResponse<List<User>> {
        var lastException: Exception? = null
        
        repeat(maxRetries) { attempt ->
            try {
                return client.users.getUsers()
            } catch (e: CarespaceNetworkException) {
                lastException = e
                if (attempt < maxRetries - 1) {
                    val delayMs = 1000L * (attempt + 1) // Exponential backoff
                    delay(delayMs)
                    println("Network error, retrying in ${delayMs}ms... (attempt ${attempt + 2})")
                }
            } catch (e: CarespaceTimeoutException) {
                lastException = e
                if (attempt < maxRetries - 1) {
                    delay(2000L) // Fixed delay for timeout
                    println("Request timeout, retrying... (attempt ${attempt + 2})")
                }
            }
        }
        
        throw lastException ?: CarespaceException("Max retries exceeded")
    }
}
```

### Authentication Refresh Flow

```kotlin
class AuthenticatedRepository(private val client: CarespaceClient) {
    
    suspend fun <T> executeWithTokenRefresh(operation: suspend () -> T): T {
        try {
            return operation()
        } catch (e: CarespaceAuthenticationException) {
            println("Token expired, attempting refresh...")
            
            try {
                val refreshToken = client.getStoredRefreshToken()
                val newTokens = client.auth.refreshToken(refreshToken)
                
                if (newTokens.success && newTokens.data != null) {
                    client.setApiKey(newTokens.data.accessToken)
                    return operation() // Retry with new token
                } else {
                    throw CarespaceAuthenticationException("Failed to refresh token")
                }
            } catch (refreshError: Exception) {
                // Redirect to login
                throw CarespaceAuthenticationException("Please log in again", refreshError)
            }
        }
    }
}
```

### Validation Error Handling

```kotlin
suspend fun createUserWithValidation(request: CreateUserRequest): ApiResponse<User> {
    try {
        return client.users.createUser(request)
    } catch (e: CarespaceValidationException) {
        // Handle specific validation errors
        e.errorDetails?.let { errors ->
            errors.forEach { field, messages ->
                when (field) {
                    "email" -> handleEmailErrors(messages)
                    "password" -> handlePasswordErrors(messages)
                    "phone" -> handlePhoneErrors(messages)
                    else -> handleGeneralFieldError(field, messages)
                }
            }
        }
        throw e
    }
}

fun handleEmailErrors(messages: List<String>) {
    messages.forEach { message ->
        when {
            message.contains("already exists") -> {
                // Show email already exists dialog
                showEmailExistsDialog()
            }
            message.contains("invalid format") -> {
                // Highlight email field with error
                showEmailFormatError()
            }
        }
    }
}
```

### Rate Limiting with Exponential Backoff

```kotlin
class RateLimitedClient(private val client: CarespaceClient) {
    
    suspend fun <T> executeWithRateLimit(
        operation: suspend () -> T,
        maxRetries: Int = 5
    ): T {
        var retryCount = 0
        
        while (retryCount <= maxRetries) {
            try {
                return operation()
            } catch (e: CarespaceRateLimitException) {
                if (retryCount == maxRetries) {
                    throw e
                }
                
                val delaySeconds = e.retryAfterSeconds ?: calculateBackoffDelay(retryCount)
                println("Rate limited. Retrying after ${delaySeconds}s...")
                
                delay(delaySeconds * 1000L)
                retryCount++
            }
        }
        
        throw CarespaceException("Rate limit retry attempts exhausted")
    }
    
    private fun calculateBackoffDelay(retryCount: Int): Long {
        return minOf(2.0.pow(retryCount).toLong(), 60L) // Max 60 seconds
    }
}
```

### Error Recovery Patterns

```kotlin
class ResilientDataManager(private val client: CarespaceClient) {
    
    suspend fun syncDataWithFallback(): List<User> {
        return try {
            // Try primary data source
            val response = client.users.getUsers()
            if (response.success) {
                response.data
            } else {
                getCachedUsers() // Fallback to cache
            }
        } catch (e: CarespaceNetworkException) {
            println("Network unavailable, using cached data")
            getCachedUsers()
        } catch (e: CarespaceException) {
            println("API error: ${e.message}, using empty state")
            emptyList()
        }
    }
    
    private suspend fun getCachedUsers(): List<User> {
        // Return cached data or empty list
        return localDatabase.getUsers()
    }
}
```

### UI Error State Management

```kotlin
// For Android ViewModels
class ErrorAwareViewModel(private val client: CarespaceClient) : ViewModel() {
    
    data class UiState(
        val isLoading: Boolean = false,
        val data: List<User> = emptyList(),
        val error: UiError? = null
    )
    
    sealed class UiError {
        object NetworkError : UiError()
        object AuthenticationError : UiError()
        data class ValidationError(val fields: Map<String, List<String>>) : UiError()
        data class GenericError(val message: String) : UiError()
    }
    
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val response = client.users.getUsers()
                if (response.success) {
                    _uiState.value = UiState(data = response.data)
                } else {
                    _uiState.value = UiState(error = UiError.GenericError(response.error ?: "Unknown error"))
                }
            } catch (e: Exception) {
                val error = when (e) {
                    is CarespaceNetworkException -> UiError.NetworkError
                    is CarespaceAuthenticationException -> UiError.AuthenticationError
                    is CarespaceValidationException -> UiError.ValidationError(e.errorDetails ?: emptyMap())
                    else -> UiError.GenericError(e.message ?: "Unknown error")
                }
                _uiState.value = UiState(error = error)
            }
        }
    }
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

The SDK provides base ViewModels for common operations. Here are complete examples:

#### Base ViewModel Pattern

```kotlin
abstract class CarespaceViewModel(
    protected val carespace: CarespaceClient
) : ViewModel() {
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    protected fun <T> executeAsync(
        onSuccess: (T) -> Unit = {},
        onError: (Exception) -> Unit = {},
        operation: suspend () -> T
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val result = operation()
                onSuccess(result)
            } catch (e: Exception) {
                _error.value = e.message
                onError(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
```

#### Users ViewModel

```kotlin
class UsersViewModel(carespace: CarespaceClient) : CarespaceViewModel(carespace) {
    
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()
    
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()
    
    fun getUsers(page: Int = 1, limit: Int = 20, search: String? = null) {
        executeAsync(
            onSuccess = { response: PaginatedResponse<User> ->
                _users.value = response.data
            }
        ) {
            carespace.users.getUsers(page, limit, search)
        }
    }
    
    fun getUserProfile() {
        executeAsync(
            onSuccess = { response: ApiResponse<User> ->
                response.data?.let { _currentUser.value = it }
            }
        ) {
            carespace.users.getUserProfile()
        }
    }
}
```

#### Clients ViewModel

```kotlin
class ClientsViewModel(carespace: CarespaceClient) : CarespaceViewModel(carespace) {
    
    private val _clients = MutableStateFlow<List<Client>>(emptyList())
    val clients: StateFlow<List<Client>> = _clients.asStateFlow()
    
    private val _selectedClient = MutableStateFlow<Client?>(null)
    val selectedClient: StateFlow<Client?> = _selectedClient.asStateFlow()
    
    private val _clientStats = MutableStateFlow<ClientStats?>(null)
    val clientStats: StateFlow<ClientStats?> = _clientStats.asStateFlow()
    
    fun getClients(page: Int = 1, limit: Int = 20, search: String? = null) {
        executeAsync(
            onSuccess = { response: PaginatedResponse<Client> ->
                _clients.value = response.data
            }
        ) {
            carespace.clients.getClients(page, limit, search)
        }
    }
    
    fun selectClient(client: Client) {
        _selectedClient.value = client
        getClientStats(client.id)
    }
    
    fun createClient(request: CreateClientRequest) {
        executeAsync(
            onSuccess = { response: ApiResponse<Client> ->
                response.data?.let { newClient ->
                    _clients.value = _clients.value + newClient
                }
            }
        ) {
            carespace.clients.createClient(request)
        }
    }
    
    private fun getClientStats(clientId: String) {
        executeAsync(
            onSuccess = { response: ApiResponse<ClientStats> ->
                _clientStats.value = response.data
            }
        ) {
            carespace.clients.getClientStats(clientId)
        }
    }
}
```

#### Programs ViewModel

```kotlin
class ProgramsViewModel(carespace: CarespaceClient) : CarespaceViewModel(carespace) {
    
    private val _programs = MutableStateFlow<List<Program>>(emptyList())
    val programs: StateFlow<List<Program>> = _programs.asStateFlow()
    
    private val _selectedProgram = MutableStateFlow<Program?>(null)
    val selectedProgram: StateFlow<Program?> = _selectedProgram.asStateFlow()
    
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()
    
    fun getPrograms(
        page: Int = 1, 
        limit: Int = 20, 
        category: ProgramCategory? = null
    ) {
        executeAsync(
            onSuccess = { response: PaginatedResponse<Program> ->
                _programs.value = response.data
            }
        ) {
            carespace.programs.getPrograms(page, limit, category = category)
        }
    }
    
    fun selectProgram(program: Program) {
        _selectedProgram.value = program
        getProgramExercises(program.id)
    }
    
    fun createProgram(request: CreateProgramRequest) {
        executeAsync(
            onSuccess = { response: ApiResponse<Program> ->
                response.data?.let { newProgram ->
                    _programs.value = _programs.value + newProgram
                }
            }
        ) {
            carespace.programs.createProgram(request)
        }
    }
    
    private fun getProgramExercises(programId: String) {
        executeAsync(
            onSuccess = { response: PaginatedResponse<Exercise> ->
                _exercises.value = response.data
            }
        ) {
            carespace.programs.getProgramExercises(programId)
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

| Platform | Status | Minimum Version | Target Release |
|----------|--------|----------------|----------------|
| Android | ✅ Supported | API 24+ | Available Now |
| JVM | ✅ Supported | Java 11+ | Available Now |
| iOS | 🚧 In Development | iOS 13+ | Q2 2024 |
| JavaScript | 📋 Planned | ES2018+ | Q3 2024 |

### Platform Roadmap

#### ✅ **Currently Supported**

**Android (API 24+)**
- Full SDK functionality
- Lifecycle-aware components
- Secure token storage with EncryptedSharedPreferences
- Jetpack Compose integration
- Material Design 3 support

**JVM (Java 11+)**
- Complete API coverage
- Server-side integration support
- Console application compatibility
- Spring Boot integration ready

#### 🚧 **iOS - In Development (Target: Q2 2024)**

**Planned Features:**
- Native iOS SDK with Swift interoperability
- UIKit and SwiftUI integration examples
- Keychain integration for secure storage
- CocoaPods and Swift Package Manager support
- iOS-specific networking optimizations

**Current Progress:**
- ✅ Core networking layer completed
- ✅ Authentication flow implemented
- 🔄 iOS-specific secure storage (70% complete)
- 🔄 SwiftUI example app (50% complete)
- ⏳ CocoaPods integration (planned)

#### 📋 **JavaScript - Planned (Target: Q3 2024)**

**Planned Features:**
- Browser and Node.js compatibility
- NPM package distribution
- TypeScript definitions
- React/Vue.js integration examples
- LocalStorage/SessionStorage for token management

**Development Phases:**
- **Phase 1** (Q3 2024): Core JavaScript SDK
- **Phase 2** (Q4 2024): Framework-specific integrations
- **Phase 3** (Q1 2025): Advanced features and optimizations

### Feature Comparison

| Feature | Android | JVM | iOS | JavaScript |
|---------|---------|-----|-----|------------|
| Authentication | ✅ | ✅ | 🚧 | 📋 |
| Users API | ✅ | ✅ | 🚧 | 📋 |
| Clients API | ✅ | ✅ | 🚧 | 📋 |
| Programs API | ✅ | ✅ | 🚧 | 📋 |
| Secure Storage | ✅ | ✅ | 🚧 | 📋 |
| Offline Support | ✅ | ⏳ | 📋 | 📋 |
| Push Notifications | ✅ | ❌ | 📋 | ❌ |
| File Upload | ✅ | ✅ | 🚧 | 📋 |

**Legend:**
- ✅ Supported
- 🚧 In Development  
- 📋 Planned
- ⏳ Future Consideration
- ❌ Not Applicable

### Platform-Specific Limitations

#### Current Limitations

**Android:**
- Minimum API level 24 (Android 7.0)
- Some advanced features require API 26+

**JVM:**
- No built-in UI components (server-side focused)
- Secure storage depends on OS keystore

#### Future Platform Considerations

- **Linux**: Server deployment support (included with JVM)
- **macOS**: Native app support (considering for 2025)
- **Windows**: Desktop app support (considering for 2025)
- **WebAssembly**: High-performance web applications (exploring)

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