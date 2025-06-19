# Carespace Android Example App

This Android application demonstrates how to integrate the Carespace Kotlin SDK into a modern Android app using Jetpack Compose, MVVM architecture, and dependency injection.

## Features Demonstrated

- **Authentication**: Login flow with secure token storage
- **Users Management**: List and view user profiles
- **Clients Management**: Browse and manage client information  
- **Programs Management**: View rehabilitation programs and exercises
- **Error Handling**: Comprehensive error states and user feedback
- **Loading States**: Progress indicators and loading management
- **Navigation**: Multi-screen navigation with Compose Navigation
- **Dependency Injection**: Koin integration for clean architecture

## Architecture

### MVVM Pattern

The app follows the Model-View-ViewModel pattern:

- **Models**: Data classes from the Carespace SDK
- **Views**: Jetpack Compose UI screens
- **ViewModels**: SDK ViewModels managing state and business logic

### Project Structure

```
android-app/
├── src/main/kotlin/com/carespace/example/android/
│   ├── CarespaceExampleApplication.kt    # Application class with DI setup
│   ├── MainActivity.kt                   # Main activity with navigation
│   └── ui/
│       ├── screens/                      # Compose screens
│       │   ├── HomeScreen.kt            # Dashboard with navigation
│       │   ├── LoginScreen.kt           # Authentication screen
│       │   ├── UsersScreen.kt           # Users list and management
│       │   ├── ClientsScreen.kt         # Clients list and management
│       │   └── ProgramsScreen.kt        # Programs and exercises
│       └── theme/                       # Material Design 3 theming
│           ├── Color.kt
│           ├── Theme.kt
│           └── Type.kt
├── build.gradle.kts                     # App-level build configuration
└── AndroidManifest.xml
```

## Getting Started

### Prerequisites

- **Android Studio Hedgehog+**
- **Android SDK 24+** (Android 7.0)
- **Kotlin 1.9.20+**
- **Android device or emulator**

### Setup Instructions

1. **Open the project in Android Studio**
   ```bash
   cd examples/android-app
   # Open android-app directory in Android Studio
   ```

2. **Sync Gradle dependencies**
   - Android Studio will automatically sync when you open the project
   - Or manually: `./gradlew sync`

3. **Run the application**
   ```bash
   ./gradlew installDebug
   # Or use the "Run" button in Android Studio
   ```

### Configuration

The app is pre-configured to use the development environment. To change environments:

```kotlin
// In CarespaceExampleApplication.kt
val carespace = CarespaceAndroidClient.forProduction(this, "your-api-key")
```

## Key Components

### Application Setup

```kotlin
// CarespaceExampleApplication.kt
class CarespaceExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CarespaceExampleApplication)
            modules(appModule)
        }
    }
}

val appModule = module {
    single { CarespaceAndroidClient.forDevelopment(get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { UsersViewModel(get()) }
    viewModel { ClientsViewModel(get()) }
    viewModel { ProgramsViewModel(get()) }
}
```

### Navigation Structure

```kotlin
// MainActivity.kt
@Composable
fun CarespaceExampleApp() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = koinViewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "home" else "login"
    ) {
        composable("login") { LoginScreen(...) }
        composable("home") { HomeScreen(...) }
        composable("users") { UsersScreen(...) }
        composable("clients") { ClientsScreen(...) }
        composable("programs") { ProgramsScreen(...) }
    }
}
```

### Screen Examples

#### Login Screen Features

- Email/password input with validation
- Loading states during authentication
- Error handling with user-friendly messages
- Demo credentials for testing
- Automatic navigation on successful login

#### Users Screen Features

- Paginated list of users
- Search functionality
- User role and status indicators
- Pull-to-refresh
- Error state handling

#### Clients Screen Features

- Client list with medical information
- Client statistics and session tracking
- Create new client functionality
- Filter and search capabilities

#### Programs Screen Features

- Program categorization
- Exercise details and instructions
- Difficulty levels and duration
- Program assignment to clients

## Dependencies

### Core Dependencies

```kotlin
// build.gradle.kts
dependencies {
    implementation(project(":carespace-sdk"))
    
    // Android & Compose
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.navigation:navigation-compose:2.7.4")
    
    // Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    
    // Dependency Injection
    implementation("io.insert-koin:koin-android:3.5.0")
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")
}
```

### SDK Integration

The app integrates the Carespace SDK through:

1. **Client initialization** in the Application class
2. **ViewModel injection** using Koin
3. **StateFlow observation** in Compose screens
4. **Error handling** with user feedback
5. **Lifecycle management** for API calls

## Testing

### Running Tests

```bash
# Unit tests
./gradlew testDebugUnitTest

# Instrumented tests (requires device/emulator)
./gradlew connectedAndroidTest

# All tests
./gradlew test
```

### Test Structure

- **Unit Tests**: ViewModel and business logic testing
- **UI Tests**: Compose screen testing with test rules
- **Integration Tests**: End-to-end flow testing

## Demo Flow

### 1. Authentication
1. Launch app → Login screen appears
2. Enter demo credentials (provided on screen)
3. Tap "Login" → Loading indicator shows
4. Successful login → Navigate to Home screen

### 2. Exploring Features
1. **Home Screen**: Dashboard with feature navigation
2. **Users**: Browse user list, see roles and status
3. **Clients**: View client information and statistics  
4. **Programs**: Explore rehabilitation programs and exercises

### 3. Error Handling
- Network errors show user-friendly messages
- Validation errors highlight specific fields
- Retry mechanisms for failed operations
- Graceful degradation for offline scenarios

## Customization

### Theming

The app uses Material Design 3 with customizable themes:

```kotlin
// ui/theme/Theme.kt
@Composable
fun CarespaceExampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

### Adding New Screens

1. Create new Composable in `ui/screens/`
2. Add route to NavHost in MainActivity
3. Create ViewModel if needed
4. Add to Koin module for dependency injection

## Troubleshooting

### Common Issues

**Issue**: App crashes on startup
**Solution**: Ensure API key is configured and device has network access

**Issue**: Login fails
**Solution**: Check network connection and API endpoint configuration

**Issue**: Empty lists
**Solution**: Verify authentication token and API permissions

**Issue**: Build errors
**Solution**: Clean and rebuild project, sync Gradle dependencies

### Debug Features

- Enable logging in CarespaceConfiguration
- Use Android Studio's Network Inspector
- Check Logcat for detailed error messages
- Use debugging tools in ViewModels

## Contributing

To contribute to this example:

1. Follow the main [Contributing Guidelines](../../CONTRIBUTING.md)
2. Ensure UI follows Material Design 3 principles
3. Add tests for new screens or ViewModels
4. Update this README for significant changes

## Resources

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Koin Documentation](https://insert-koin.io/)
- [Android Architecture Guide](https://developer.android.com/topic/architecture)
- [Carespace SDK Documentation](../../README.md)

---

This example demonstrates best practices for integrating the Carespace SDK into production Android applications. Use it as a starting point for your own healthcare applications! 🏥📱