# Contributing to Carespace Kotlin SDK

We welcome contributions to the Carespace Kotlin SDK! This guide will help you get started with contributing to the project.

## Development Setup

### Prerequisites

- **Kotlin 1.9.20+**
- **Java 11+** (for JVM targets)
- **Android Studio Arctic Fox+** (for Android development)
- **Gradle 8.0+**
- **Git**

### Getting Started

1. **Fork the repository**
   ```bash
   git clone https://github.com/your-username/carespace-kotlin-sdk.git
   cd carespace-kotlin-sdk
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run tests**
   ```bash
   ./gradlew test
   ```

4. **Run example applications**
   ```bash
   # JVM Example
   ./gradlew :examples:jvm-app:run
   
   # Android Example (requires Android device/emulator)
   ./gradlew :examples:android-app:installDebug
   ```

## Project Structure

```
carespace-kotlin-sdk/
├── carespace-sdk/              # Main SDK module
│   ├── src/
│   │   ├── commonMain/         # Shared Kotlin code
│   │   ├── androidMain/        # Android-specific code
│   │   └── commonTest/         # Shared tests
│   └── build.gradle.kts
├── examples/
│   ├── android-app/            # Android example app
│   └── jvm-app/               # JVM console example
├── build.gradle.kts           # Root build configuration
└── settings.gradle.kts
```

## Coding Standards

### Kotlin Style Guide

We follow the [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html) with these additions:

- **Indentation**: 4 spaces (no tabs)
- **Line length**: 120 characters maximum
- **Naming**: 
  - Classes: `PascalCase`
  - Functions/Variables: `camelCase`
  - Constants: `SCREAMING_SNAKE_CASE`
  - Packages: `lowercase.separated.by.dots`

### Code Organization

- **API Classes**: Place in `api/` package
- **Models**: Place in `models/` package  
- **Exceptions**: Place in `exceptions/` package
- **Configuration**: Place in `configuration/` package
- **Android-specific**: Place in `androidMain/` source set

### Documentation

- All public APIs must have KDoc comments
- Include usage examples for complex functions
- Document parameters, return values, and exceptions
- Use `@since` tags for version information

Example:
```kotlin
/**
 * Retrieves a paginated list of users from the Carespace API.
 *
 * @param page The page number to retrieve (1-based indexing)
 * @param limit Maximum number of users per page (1-100)
 * @param search Optional search query to filter users by name or email
 * @param role Optional role filter to limit results
 * @param isActive Optional filter for active/inactive users
 * @return Paginated response containing the list of users
 * @throws CarespaceAuthenticationException if the API key is invalid
 * @throws CarespaceValidationException if parameters are invalid
 * @since 1.0.0
 */
suspend fun getUsers(
    page: Int = 1,
    limit: Int = 20,
    search: String? = null,
    role: UserRole? = null,
    isActive: Boolean? = null
): PaginatedResponse<User>
```

## Testing Guidelines

### Test Structure

- **Unit Tests**: Test individual functions and classes
- **Integration Tests**: Test API interactions with mock responses
- **Android Tests**: Test Android-specific functionality

### Writing Tests

```kotlin
class UsersApiTest {
    @Test
    fun `getUsers should return paginated response`() = runTest {
        // Given
        val mockClient = mockCarespaceClient()
        val usersApi = UsersApi(mockClient)
        
        // When
        val result = usersApi.getUsers(page = 1, limit = 10)
        
        // Then
        assertTrue(result.success)
        assertEquals(10, result.data.size)
    }
}
```

### Test Requirements

- All new features must include unit tests
- Maintain test coverage above 80%
- Use descriptive test names with backticks for readability
- Mock external dependencies using MockK
- Test both success and error scenarios

## Pull Request Process

### Before Submitting

1. **Run all tests**
   ```bash
   ./gradlew test
   ```

2. **Run linting**
   ```bash
   ./gradlew ktlintCheck
   ```

3. **Build all targets**
   ```bash
   ./gradlew build
   ```

4. **Update documentation** if you've changed public APIs

### PR Guidelines

1. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Write descriptive commit messages**
   ```
   Add user profile update functionality
   
   - Add updateUserProfile method to UsersApi
   - Include validation for required fields
   - Add corresponding unit tests
   - Update documentation with usage examples
   ```

3. **Keep PRs focused** - One feature or fix per PR

4. **Include tests** for new functionality

5. **Update documentation** for public API changes

### PR Template

When creating a PR, please include:

- **Summary**: Brief description of changes
- **Type**: Feature, Bug Fix, Documentation, etc.
- **Testing**: How you tested the changes
- **Breaking Changes**: Any breaking changes and migration guide
- **Checklist**: Confirm tests pass, documentation updated, etc.

## Release Process

### Version Numbering

We follow [Semantic Versioning](https://semver.org/):

- **MAJOR**: Breaking changes
- **MINOR**: New features (backward compatible)
- **PATCH**: Bug fixes (backward compatible)

### Creating Releases

1. Update version in `build.gradle.kts`
2. Update CHANGELOG.md
3. Create release tag
4. Publish to Maven Central (maintainers only)

## API Design Guidelines

### Consistency

- Use consistent naming across all APIs
- Follow REST conventions where applicable
- Return `ApiResponse<T>` for single items
- Return `PaginatedResponse<T>` for collections

### Error Handling

- Create specific exception types for different error scenarios
- Include detailed error messages
- Provide error codes when possible

### Async Operations

- All API calls should be suspend functions
- Use StateFlow for observable state in ViewModels
- Handle cancellation properly

## Common Issues

### Build Problems

**Issue**: `Could not resolve kotlinx-coroutines`
**Solution**: Ensure you're using compatible Kotlin and coroutines versions

**Issue**: Android tests failing
**Solution**: Make sure you have an Android device/emulator connected

### Testing Issues

**Issue**: Tests hang on suspend functions
**Solution**: Use `runTest { }` from kotlinx-coroutines-test

## Getting Help

- **Questions**: Open a discussion on GitHub
- **Bugs**: Create an issue with reproduction steps
- **Features**: Open an issue with detailed requirements
- **Discord**: Join our [Discord community](https://discord.gg/carespace)

## Code of Conduct

We are committed to providing a welcoming and inclusive environment. Please:

- Be respectful and professional
- Welcome newcomers and help them learn
- Focus on constructive feedback
- Respect different viewpoints and experiences

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

Thank you for contributing to Carespace Kotlin SDK! 🚀