# Carespace SDK

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?logo=javascript&logoColor=black)](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
[![Swift](https://img.shields.io/badge/Swift-FA7343?logo=swift&logoColor=white)](https://swift.org/)

Official SDKs for the Carespace API - Build powerful healthcare and rehabilitation applications with ease.

## Overview

Carespace provides comprehensive SDKs for multiple programming languages, enabling developers to integrate with the Carespace API seamlessly. Whether you're building web applications, mobile apps, or backend services, we have you covered.

## 🚀 Available SDKs

### TypeScript SDK
**Full-featured SDK with complete type safety**
- 🔒 Complete TypeScript definitions
- 🚀 Modern async/await support
- 📦 Tree-shakeable modules
- 🛡️ Built-in error handling

```bash
npm install @carespace/sdk-ts
```

[📖 TypeScript Documentation](https://github.com/fusuma/carespace-typescript-sdk/blob/main/README.md) | [🔗 NPM Package](https://www.npmjs.com/package/@carespace/sdk-ts)

### JavaScript SDK
**Lightweight, dependency-free JavaScript SDK**
- ⚡ Zero dependencies
- 🌐 Modern ES6 modules
- 🔄 Native fetch API
- 📱 Browser and Node.js compatible

```bash
npm install @carespace/sdk-js
```

[📖 JavaScript Documentation](https://github.com/fusuma/carespace-javascript-sdk/blob/main/README.md) | [🔗 NPM Package](https://www.npmjs.com/package/@carespace/sdk-js)

### Swift SDK
**Native iOS/macOS SDK with async/await**
- 📱 iOS 13+ / macOS 10.15+ support
- ⚡ Modern async/await syntax
- 🔒 Type-safe Swift implementation
- 🧩 SwiftUI ready

```swift
.package(url: "https://github.com/carespace/swift-sdk.git", from: "1.0.0")
```

[📖 Swift Documentation](https://github.com/fusuma/carespace-swift-sdk/blob/main/README.md) | [🔗 Swift Package](https://github.com/fusuma/carespace-swift-sdk)

### .NET SDK
**Enterprise-ready SDK with full async support**
- 🔒 Type-safe C# implementation with nullable reference types
- ⚡ Modern async/await patterns
- 🛡️ Comprehensive error handling
- 🔄 Built-in retry logic with exponential backoff
- 📦 Full dependency injection support

```bash
dotnet add package Carespace.SDK
```

[📖 .NET Documentation](https://github.com/fusuma/carespace-dotnet-sdk/blob/main/README.md) | [🔗 NuGet Package](https://www.nuget.org/packages/Carespace.SDK)

### Python SDK
**Comprehensive async/await Python SDK**
- 🐍 Python 3.8+ support with full type hints
- ⚡ Modern async/await syntax
- 🔒 Type-safe implementation
- 📦 Easy pip installation

```bash
pip install carespace-sdk
```

[📖 Python Documentation](https://github.com/fusuma/carespace-python-sdk/blob/main/README.md) | [🔗 PyPI Package](https://pypi.org/project/carespace-sdk/)

### Kotlin SDK
**Multiplatform SDK with Android optimization**
- 🚀 Kotlin Multiplatform (Android, JVM)
- ⚡ Coroutines with suspend functions
- 🔒 Type-safe data classes
- 📱 Android lifecycle awareness
- 🧪 Comprehensive testing

```bash
implementation("com.carespace:sdk-kotlin:1.0.0")
```

[📖 Kotlin Documentation](https://github.com/fusuma/carespace-kotlin-sdk/blob/main/README.md) | [🔗 Maven Central](https://search.maven.org/artifact/com.carespace/sdk-kotlin)

### Unreal Engine SDK
**Native C++ SDK for Unreal Engine**
- 🎮 Unreal Engine 5.0+ support
- ⚡ Async Blueprint nodes
- 🔒 Type-safe C++ implementation
- 🎯 Game development optimized
- 📦 Plugin-based integration

```cpp
// Add to your .uproject plugins section
"CarespaceSDK": { "Enabled": true }
```

[📖 Unreal Documentation](https://github.com/fusuma/carespace-unreal-sdk/blob/main/README.md) | [🔗 Unreal Marketplace](https://www.unrealengine.com/marketplace/carespace-sdk)

## 🏃‍♂️ Quick Start

### TypeScript/JavaScript

```typescript
import { CarespaceAPI } from '@carespace/sdk-ts';

const carespace = new CarespaceAPI({
  apiKey: 'your-api-key',
  baseURL: 'https://api.carespace.ai'
});

// Authenticate
const loginResponse = await carespace.auth.login({
  email: 'user@example.com',
  password: 'password'
});

// Get users
const users = await carespace.users.getUsers();

// Create a client
const client = await carespace.clients.createClient({
  name: 'John Doe',
  email: 'john@example.com'
});
```

### Swift

```swift
import CarespaceSDK

let carespace = CarespaceAPI(apiKey: "your-api-key")

// Authenticate
let loginRequest = LoginRequest(email: "user@example.com", password: "password")
let loginResponse = try await carespace.auth.login(loginRequest)

// Get users
let usersResponse = try await carespace.users.getUsers()

// Create a client
let clientRequest = CreateClientRequest(name: "John Doe", email: "john@example.com")
let client = try await carespace.clients.createClient(clientRequest)
```

### .NET/C#

```csharp
using CarespaceSDK;
using CarespaceSDK.Models;

using var carespace = CarespaceClient.CreateForDevelopment("your-api-key");

// Authenticate
var loginResponse = await carespace.LoginAndSetTokenAsync("user@example.com", "password");

// Get users
var users = await carespace.QuickGetUsersAsync(limit: 10);

// Create a client
var clientRequest = new CreateClientRequest
{
    Name = "John Doe",
    Email = "john@example.com"
};
var client = await carespace.Clients.CreateClientAsync(clientRequest);
```

### Python

```python
import asyncio
from carespace_sdk import CarespaceClient

async def main():
    async with CarespaceClient(api_key="your-api-key") as carespace:
        # Authenticate
        login_response = await carespace.login_and_set_token("user@example.com", "password")
        
        # Get users
        users = await carespace.quick_get_users(limit=10)
        
        # Create a client
        from carespace_sdk import CreateClientRequest
        client_data = CreateClientRequest(name="John Doe", email="john@example.com")
        client = await carespace.clients.create_client(client_data)

asyncio.run(main())
```

## 🌟 Features

### Core Functionality
- **Authentication** - Login, logout, token management, password reset
- **User Management** - CRUD operations, profiles, settings, permissions
- **Client Management** - Patient records, medical history, program assignments
- **Program Management** - Exercise programs, templates, customization
- **Analytics** - Progress tracking, reports, statistics

### Developer Experience
- **Type Safety** - Full TypeScript support with generated types
- **Error Handling** - Comprehensive error types and handling
- **Documentation** - Extensive docs with examples
- **Testing** - Built-in test suites
- **Modern APIs** - Async/await, promises, modern JavaScript

### Platform Support
- **Web** - React, Vue, Angular, vanilla JavaScript
- **Mobile** - React Native, iOS (Swift), Android (Kotlin)
- **Backend** - Node.js, Python, .NET
- **Desktop** - Electron, macOS native, Windows (.NET)

## 📚 Documentation

### API Reference
- [🔗 API Documentation](https://docs.carespace.ai/api)
- [🔗 OpenAPI Specification](./shared/swagger-merged-corrected.json)
- [🔗 Postman Collection](https://www.postman.com/carespace-api)

### SDK Guides
- [📖 TypeScript SDK Guide](https://github.com/fusuma/carespace-typescript-sdk/blob/main/README.md)
- [📖 JavaScript SDK Guide](https://github.com/fusuma/carespace-javascript-sdk/blob/main/README.md)
- [📖 Swift SDK Guide](https://github.com/fusuma/carespace-swift-sdk/blob/main/README.md)
- [📖 .NET SDK Guide](https://github.com/fusuma/carespace-dotnet-sdk/blob/main/README.md)
- [📖 Python SDK Guide](https://github.com/fusuma/carespace-python-sdk/blob/main/README.md)
- [📖 Kotlin SDK Guide](https://github.com/fusuma/carespace-kotlin-sdk/blob/main/README.md)
- [📖 Unreal SDK Guide](https://github.com/fusuma/carespace-unreal-sdk/blob/main/README.md)

### Examples & Tutorials
- [💡 Getting Started Guide](https://docs.carespace.ai/getting-started)
- [🎯 Integration Examples](https://github.com/carespace/examples)
- [🎓 Video Tutorials](https://www.youtube.com/carespace-dev)

## 🛠️ Development

### Project Structure

```
carespace-sdk/ (main repository)
├── README.md                   # Main documentation
├── .gitmodules                # Submodule configuration
├── typescript/                # → fusuma/carespace-typescript-sdk
├── javascript/                # → fusuma/carespace-javascript-sdk  
├── swift/                     # → fusuma/carespace-swift-sdk
├── python/                    # → fusuma/carespace-python-sdk
├── dotnet/                    # → fusuma/carespace-dotnet-sdk
├── kotlin/                    # → fusuma/carespace-kotlin-sdk
├── unreal/                    # → fusuma/carespace-unreal-sdk
└── shared/                    # Shared resources (OpenAPI specs)
```

### Building from Source

```bash
# Clone the main repository with submodules
git clone --recursive https://github.com/fusuma/carespace-sdk.git
cd carespace-sdk

# Or if already cloned, initialize submodules
git submodule update --init --recursive

# Build individual SDKs
# TypeScript SDK
cd typescript && npm install && npm run build && cd ..

# JavaScript SDK  
cd javascript && npm install && cd ..

# Swift SDK
cd swift && swift build && cd ..

# Python SDK
cd python && pip install -e . && cd ..

# .NET SDK
cd dotnet && dotnet build && cd ..

# Kotlin SDK
cd kotlin && ./gradlew build && cd ..

# Unreal SDK (requires Unreal Engine)
cd unreal && # Follow Unreal-specific build instructions
```

### Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

### Testing

Each SDK includes comprehensive test suites:

```bash
# TypeScript
cd typescript && npm test

# JavaScript
cd javascript && npm test

# Swift
cd swift && swift test

# Python
cd python && python -m pytest

# .NET
cd dotnet && dotnet test

# Kotlin
cd kotlin && ./gradlew test

# Unreal
cd unreal && # Follow Unreal-specific test instructions
```

## 🔐 Authentication

All SDKs support multiple authentication methods:

### API Key Authentication
```typescript
const carespace = new CarespaceAPI({
  apiKey: 'your-api-key'
});
```

### OAuth 2.0 / JWT
```typescript
// Login to get access token
const response = await carespace.auth.login({
  email: 'user@example.com',
  password: 'password'
});

// Set token for subsequent requests
carespace.setApiKey(response.data.access_token);
```

### Environment Variables
```bash
# .env file
CARESPACE_API_KEY=your-api-key
CARESPACE_BASE_URL=https://api.carespace.ai
```

## 🌍 Environments

### Development
```
Base URL: https://api-dev.carespace.ai
Purpose: Development and testing
Rate Limits: Higher limits for development
```

### Staging  
```
Base URL: https://api-staging.carespace.ai
Purpose: Pre-production testing
Rate Limits: Production-like limits
```

### Production
```
Base URL: https://api.carespace.ai
Purpose: Live applications
Rate Limits: Standard production limits
```

## 📊 Usage Examples

### Healthcare Dashboard
```typescript
// Get client overview
const clients = await carespace.clients.getClients({ limit: 10 });
const stats = await Promise.all(
  clients.data.data.map(client => 
    carespace.clients.getClientStats(client.id)
  )
);

// Display progress metrics
const totalSessions = stats.reduce((sum, stat) => 
  sum + stat.data.total_sessions, 0
);
```

### Exercise Program Builder
```typescript
// Create rehabilitation program
const program = await carespace.programs.createProgram({
  name: 'Post-Surgery Knee Rehabilitation',
  category: 'rehabilitation',
  difficulty: 'beginner',
  duration: 30
});

// Add exercises
const exercises = [
  { name: 'Knee Flexion', duration: 60, repetitions: 10 },
  { name: 'Heel Slides', duration: 45, repetitions: 15 },
  { name: 'Ankle Pumps', duration: 30, repetitions: 20 }
];

for (const exercise of exercises) {
  await carespace.programs.addExerciseToProgram(program.data.id, exercise);
}
```

### Patient Progress Tracking
```swift
// Swift example
let clientId = "client-123"
let programs = try await carespace.clients.getClientPrograms(clientId)

for program in programs.data {
    let exercises = try await carespace.programs.getProgramExercises(program.id)
    print("Program: \\(program.name) - \\(exercises.data.count) exercises")
}
```

## 🤝 Community & Support

### Get Help
- 💬 [Discord Community](https://discord.gg/carespace)
- 📧 Email: [support@carespace.ai](mailto:support@carespace.ai)
- 🐛 [Report Issues](https://github.com/carespace/sdk-monorepo/issues)
- 📖 [Documentation](https://docs.carespace.ai)

### Stay Updated
- 🐦 [Twitter @carespace_dev](https://twitter.com/carespace_dev)
- 📝 [Developer Blog](https://blog.carespace.ai)
- 📺 [YouTube Channel](https://youtube.com/carespace-dev)
- 📰 [Newsletter](https://carespace.ai/newsletter)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

Special thanks to all contributors and the open-source community for making this project possible.

---

<p align="center">
  <strong>Built with ❤️ by the Carespace Team</strong><br>
  <a href="https://carespace.ai">🌐 Website</a> •
  <a href="https://docs.carespace.ai">📖 Docs</a> •
  <a href="https://github.com/carespace">🔗 GitHub</a> •
  <a href="https://twitter.com/carespace_dev">🐦 Twitter</a>
</p>