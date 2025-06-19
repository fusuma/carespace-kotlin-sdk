# Carespace TypeScript SDK

A comprehensive TypeScript SDK for the Carespace API with full type safety and modern async/await support.

## Features

- 🔒 **Full Type Safety** - Complete TypeScript definitions generated from OpenAPI spec
- 🚀 **Modern Async/Await** - Promise-based API with async/await support
- 🛡️ **Built-in Error Handling** - Comprehensive error types and handling
- 📦 **Tree Shakeable** - Import only what you need
- 🔧 **Configurable** - Flexible configuration options
- 📖 **Well Documented** - Extensive documentation and examples

## Requirements

- Node.js 16+ 
- TypeScript 4.5+

## Installation

```bash
npm install @carespace/sdk-ts
# or
yarn add @carespace/sdk-ts
# or
pnpm add @carespace/sdk-ts
```

## Quick Start

```typescript
import { CarespaceAPI } from '@carespace/sdk-ts';

// Initialize the SDK
const carespace = new CarespaceAPI({
  baseURL: 'https://api.carespace.ai', // Optional, defaults to dev environment
  apiKey: 'your-api-key',
  timeout: 30000 // Optional, defaults to 30 seconds
});

// Use the API
try {
  const users = await carespace.users.getUsers();
  console.log('Users:', users.data);
} catch (error) {
  console.error('Error:', error.message);
}
```

## Configuration

### CarespaceConfig

```typescript
interface CarespaceConfig {
  baseURL?: string;        // Default: 'https://api-dev.carespace.ai'
  apiKey?: string;         // Your API key
  timeout?: number;        // Request timeout in ms (default: 30000)
}
```

### Environment-specific Setup

```typescript
// Development
const devCarespace = new CarespaceAPI({
  baseURL: 'https://api-dev.carespace.ai',
  apiKey: process.env.CARESPACE_DEV_API_KEY
});

// Production
const prodCarespace = new CarespaceAPI({
  baseURL: 'https://api.carespace.ai',
  apiKey: process.env.CARESPACE_API_KEY
});
```

## Usage Examples

### Authentication

```typescript
import { CarespaceAPI } from '@carespace/sdk-ts';

const carespace = new CarespaceAPI();

// Login
try {
  const response = await carespace.auth.login({
    email: 'user@example.com',
    password: 'your-password'
  });
  
  // Set the API key from login response
  carespace.setApiKey(response.data.access_token);
  
  console.log('Logged in successfully!');
} catch (error) {
  console.error('Login failed:', error.message);
}

// Logout
try {
  await carespace.auth.logout();
  console.log('Logged out successfully!');
} catch (error) {
  console.error('Logout failed:', error.message);
}

// Password reset
try {
  await carespace.auth.forgotPassword({ email: 'user@example.com' });
  console.log('Password reset email sent!');
} catch (error) {
  console.error('Password reset failed:', error.message);
}

// Change password
try {
  await carespace.auth.changePassword({
    current_password: 'old-password',
    new_password: 'new-password'
  });
  console.log('Password changed successfully!');
} catch (error) {
  console.error('Password change failed:', error.message);
}
```

### Working with Users

```typescript
// Get all users with pagination
try {
  const response = await carespace.users.getUsers({
    page: 1,
    limit: 20,
    search: 'john'
  });
  
  console.log(`Found ${response.data.total} users`);
  response.data.data.forEach(user => {
    console.log(`User: ${user.name} (${user.email})`);
  });
} catch (error) {
  console.error('Failed to fetch users:', error.message);
}

// Get specific user
try {
  const response = await carespace.users.getUser('user-id');
  const user = response.data;
  console.log(`User: ${user.name} (${user.email})`);
} catch (error) {
  console.error('Failed to fetch user:', error.message);
}

// Create user
try {
  const response = await carespace.users.createUser({
    email: 'newuser@example.com',
    name: 'John Doe',
    first_name: 'John',
    last_name: 'Doe',
    role: 'client'
  });
  
  console.log('Created user:', response.data.id);
} catch (error) {
  console.error('Failed to create user:', error.message);
}

// Update user
try {
  const response = await carespace.users.updateUser('user-id', {
    name: 'Jane Doe',
    first_name: 'Jane',
    last_name: 'Doe'
  });
  
  console.log('Updated user:', response.data.name);
} catch (error) {
  console.error('Failed to update user:', error.message);
}

// Get user profile
try {
  const response = await carespace.users.getUserProfile();
  console.log('Profile:', response.data);
} catch (error) {
  console.error('Failed to fetch profile:', error.message);
}

// Update user settings
try {
  const response = await carespace.users.updateUserSettings('user-id', {
    notifications: true,
    email_notifications: false,
    theme: 'dark',
    language: 'en'
  });
  
  console.log('Settings updated:', response.data);
} catch (error) {
  console.error('Failed to update settings:', error.message);
}
```

### Working with Clients

```typescript
// Get all clients
try {
  const response = await carespace.clients.getClients({
    page: 1,
    limit: 10,
    search: 'smith'
  });
  
  response.data.data.forEach(client => {
    console.log(`Client: ${client.name}`);
  });
} catch (error) {
  console.error('Failed to fetch clients:', error.message);
}

// Create client
try {
  const response = await carespace.clients.createClient({
    name: 'John Smith',
    email: 'john.smith@example.com',
    phone: '+1234567890',
    date_of_birth: '1985-06-15',
    gender: 'male',
    address: {
      street: '123 Main St',
      city: 'New York',
      state: 'NY',
      zip_code: '10001',
      country: 'USA'
    },
    medical_history: 'Previous knee injury',
    notes: 'Prefers morning appointments'
  });
  
  console.log('Created client:', response.data.id);
} catch (error) {
  console.error('Failed to create client:', error.message);
}

// Get client statistics
try {
  const response = await carespace.clients.getClientStats('client-id');
  const stats = response.data;
  
  console.log(`Total sessions: ${stats.total_sessions}`);
  console.log(`Completed exercises: ${stats.completed_exercises}`);
  console.log(`Average score: ${stats.average_score}`);
} catch (error) {
  console.error('Failed to fetch client stats:', error.message);
}

// Assign program to client
try {
  await carespace.clients.assignProgramToClient('client-id', 'program-id', {
    start_date: '2024-01-01',
    end_date: '2024-04-01',
    notes: 'Post-surgery rehabilitation program'
  });
  
  console.log('Program assigned successfully!');
} catch (error) {
  console.error('Failed to assign program:', error.message);
}

// Get client programs
try {
  const response = await carespace.clients.getClientPrograms('client-id');
  response.data.data.forEach(program => {
    console.log(`Program: ${program.name}`);
  });
} catch (error) {
  console.error('Failed to fetch client programs:', error.message);
}
```

### Working with Programs

```typescript
// Get all programs
try {
  const response = await carespace.programs.getPrograms({
    page: 1,
    limit: 20,
    category: 'rehabilitation'
  });
  
  response.data.data.forEach(program => {
    console.log(`Program: ${program.name} (${program.duration} min)`);
  });
} catch (error) {
  console.error('Failed to fetch programs:', error.message);
}

// Create program
try {
  const response = await carespace.programs.createProgram({
    name: 'Knee Rehabilitation Program',
    description: 'Comprehensive post-surgery knee rehabilitation',
    category: 'rehabilitation',
    difficulty: 'intermediate',
    duration: 45,
    is_template: false
  });
  
  console.log('Created program:', response.data.id);
} catch (error) {
  console.error('Failed to create program:', error.message);
}

// Add exercise to program
try {
  const response = await carespace.programs.addExerciseToProgram('program-id', {
    name: 'Knee Flexion Exercise',
    description: 'Gentle knee bending exercise',
    instructions: 'Slowly bend your knee to 90 degrees and hold for 5 seconds',
    video_url: 'https://example.com/exercise-video.mp4',
    duration: 30,
    repetitions: 10,
    sets: 3,
    rest_time: 60,
    order: 1
  });
  
  console.log('Added exercise:', response.data.name);
} catch (error) {
  console.error('Failed to add exercise:', error.message);
}

// Get program exercises
try {
  const response = await carespace.programs.getProgramExercises('program-id');
  response.data.data.forEach(exercise => {
    console.log(`Exercise: ${exercise.name} (${exercise.duration}s)`);
  });
} catch (error) {
  console.error('Failed to fetch exercises:', error.message);
}

// Duplicate program
try {
  const response = await carespace.programs.duplicateProgram('program-id', {
    name: 'Advanced Knee Rehabilitation',
    description: 'Advanced version of the program',
    copy_exercises: true
  });
  
  console.log('Duplicated program:', response.data.id);
} catch (error) {
  console.error('Failed to duplicate program:', error.message);
}

// Get program templates
try {
  const response = await carespace.programs.getProgramTemplates();
  response.data.data.forEach(template => {
    console.log(`Template: ${template.name}`);
  });
} catch (error) {
  console.error('Failed to fetch templates:', error.message);
}
```

## Error Handling

The SDK provides comprehensive error handling:

```typescript
import { CarespaceAPI } from '@carespace/sdk-ts';
import { AxiosError } from 'axios';

const carespace = new CarespaceAPI({ apiKey: 'your-api-key' });

try {
  const response = await carespace.users.getUser('invalid-id');
} catch (error) {
  if (error.response?.status === 401) {
    console.error('Authentication failed. Please check your API key.');
    // Redirect to login or refresh token
  } else if (error.response?.status === 404) {
    console.error('User not found');
  } else if (error.response?.status >= 500) {
    console.error('Server error. Please try again later.');
  } else {
    console.error('Unexpected error:', error.message);
  }
}
```

### Custom Error Handler

```typescript
const carespace = new CarespaceAPI({
  apiKey: 'your-api-key'
});

// Add response interceptor for global error handling
carespace.client.http.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => {
    if (error.response?.status === 401) {
      // Handle authentication errors globally
      console.log('Session expired, redirecting to login...');
      // Redirect logic here
    }
    return Promise.reject(error);
  }
);
```

## TypeScript Support

The SDK is built with TypeScript and provides full type definitions:

```typescript
import type { 
  User, 
  Client, 
  Program, 
  Exercise,
  LoginRequest,
  CreateUserRequest,
  UpdateClientRequest 
} from '@carespace/sdk-ts';

// All API responses are properly typed
const createUser = async (userData: CreateUserRequest): Promise<User> => {
  const response = await carespace.users.createUser(userData);
  return response.data; // Type: User
};

// Pagination responses are typed
const getUsers = async (): Promise<User[]> => {
  const response = await carespace.users.getUsers();
  return response.data.data; // Type: User[]
};
```

## Advanced Usage

### Custom Axios Configuration

```typescript
import axios from 'axios';
import { CarespaceClient } from '@carespace/sdk-ts';

// Create custom axios instance
const customAxios = axios.create({
  baseURL: 'https://api.carespace.ai',
  timeout: 60000,
  headers: {
    'X-Custom-Header': 'custom-value'
  }
});

// Use with Carespace client
const client = new CarespaceClient({ 
  baseURL: 'https://api.carespace.ai',
  apiKey: 'your-api-key' 
});
```

### Environment Variables

```typescript
// .env file
CARESPACE_API_KEY=your-api-key
CARESPACE_BASE_URL=https://api.carespace.ai

// Usage
const carespace = new CarespaceAPI({
  baseURL: process.env.CARESPACE_BASE_URL,
  apiKey: process.env.CARESPACE_API_KEY
});
```

### Using with Different Frameworks

#### React/Next.js

```typescript
import { useState, useEffect } from 'react';
import { CarespaceAPI } from '@carespace/sdk-ts';

const carespace = new CarespaceAPI({ apiKey: process.env.NEXT_PUBLIC_CARESPACE_API_KEY });

export function UsersList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await carespace.users.getUsers();
        setUsers(response.data.data);
      } catch (error) {
        console.error('Failed to fetch users:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchUsers();
  }, []);

  if (loading) return <div>Loading...</div>;

  return (
    <ul>
      {users.map(user => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}
```

#### Express.js

```typescript
import express from 'express';
import { CarespaceAPI } from '@carespace/sdk-ts';

const app = express();
const carespace = new CarespaceAPI({ apiKey: process.env.CARESPACE_API_KEY });

app.get('/api/users', async (req, res) => {
  try {
    const response = await carespace.users.getUsers();
    res.json(response.data);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});
```

## API Reference

### Main Classes

- **CarespaceAPI** - Main SDK class
- **CarespaceClient** - HTTP client wrapper
- **AuthAPI** - Authentication endpoints
- **UsersAPI** - User management endpoints
- **ClientsAPI** - Client management endpoints  
- **ProgramsAPI** - Program management endpoints

### Available Endpoints

#### Authentication (`carespace.auth`)
- `login(credentials)` - User login
- `logout()` - User logout
- `refreshToken(token)` - Refresh access token
- `forgotPassword(email)` - Request password reset
- `resetPassword(token, password)` - Reset password
- `changePassword(currentPassword, newPassword)` - Change password
- `verifyEmail(token)` - Verify email address
- `resendVerification(email)` - Resend verification email

#### Users (`carespace.users`)
- `getUsers(params?)` - Get users list
- `getUser(id)` - Get user by ID
- `createUser(data)` - Create new user
- `updateUser(id, data)` - Update user
- `deleteUser(id)` - Delete user
- `getUserProfile()` - Get current user profile
- `updateUserProfile(data)` - Update user profile
- `getUserSettings(id)` - Get user settings
- `updateUserSettings(id, settings)` - Update user settings

#### Clients (`carespace.clients`)
- `getClients(params?)` - Get clients list
- `getClient(id)` - Get client by ID
- `createClient(data)` - Create new client
- `updateClient(id, data)` - Update client
- `deleteClient(id)` - Delete client
- `getClientStats(id)` - Get client statistics
- `getClientPrograms(id, params?)` - Get client programs
- `assignProgramToClient(clientId, programId, data?)` - Assign program
- `removeClientProgram(clientId, programId)` - Remove program

#### Programs (`carespace.programs`)
- `getPrograms(params?)` - Get programs list
- `getProgram(id)` - Get program by ID
- `createProgram(data)` - Create new program
- `updateProgram(id, data)` - Update program
- `deleteProgram(id)` - Delete program
- `getProgramExercises(id, params?)` - Get program exercises
- `addExerciseToProgram(id, data)` - Add exercise to program
- `updateProgramExercise(programId, exerciseId, data)` - Update exercise
- `removeProgramExercise(programId, exerciseId)` - Remove exercise
- `duplicateProgram(id, data?)` - Duplicate program
- `getProgramTemplates(params?)` - Get program templates

## Development

```bash
# Install dependencies
npm install

# Generate types from OpenAPI spec
npm run generate

# Build the SDK
npm run build

# Run type checking
npm run typecheck

# Run linting
npm run lint

# Development mode with watch
npm run dev
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Run tests and type checking (`npm run typecheck`)
5. Commit your changes (`git commit -m 'Add amazing feature'`)
6. Push to the branch (`git push origin feature/amazing-feature`)
7. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

- 📧 Email: support@carespace.ai
- 🐛 Issues: [GitHub Issues](https://github.com/carespace/sdk-typescript/issues)
- 📖 Documentation: [API Documentation](https://docs.carespace.ai)