# Carespace JavaScript SDK

A modern JavaScript SDK for the Carespace API using ES6 modules and the Fetch API.

## Installation

```bash
npm install @carespace/sdk-js
```

## Usage

### Basic Setup

```javascript
import { CarespaceAPI } from '@carespace/sdk-js';

const carespace = new CarespaceAPI({
  baseURL: 'https://api.carespace.ai', // Optional, defaults to dev environment
  apiKey: 'your-api-key',
  timeout: 30000 // Optional, defaults to 30 seconds
});
```

### Authentication

```javascript
// Login
const loginResult = await carespace.auth.login({
  email: 'user@example.com',
  password: 'password'
});

// Set API key from login result
carespace.setApiKey(loginResult.access_token);

// Logout
await carespace.auth.logout();
```

### Working with Users

```javascript
// Get all users
const users = await carespace.users.getUsers();

// Get specific user
const user = await carespace.users.getUser('user-id');

// Create user
const newUser = await carespace.users.createUser({
  name: 'John Doe',
  email: 'john@example.com'
});

// Update user
const updatedUser = await carespace.users.updateUser('user-id', {
  name: 'Jane Doe'
});
```

### Working with Clients

```javascript
// Get all clients
const clients = await carespace.clients.getClients();

// Get specific client
const client = await carespace.clients.getClient('client-id');

// Create client
const newClient = await carespace.clients.createClient({
  name: 'Client Name',
  email: 'client@example.com'
});

// Get client programs
const programs = await carespace.clients.getClientPrograms('client-id');
```

### Working with Programs

```javascript
// Get all programs
const programs = await carespace.programs.getPrograms();

// Get specific program
const program = await carespace.programs.getProgram('program-id');

// Create program
const newProgram = await carespace.programs.createProgram({
  name: 'Rehabilitation Program',
  description: 'Post-injury rehabilitation'
});

// Get program exercises
const exercises = await carespace.programs.getProgramExercises('program-id');
```

### Error Handling

```javascript
try {
  const user = await carespace.users.getUser('invalid-id');
} catch (error) {
  if (error.message.includes('Authentication failed')) {
    // Handle authentication error
    console.log('Please check your API key');
  } else {
    // Handle other errors
    console.error('API Error:', error.message);
  }
}
```

### Advanced Usage

#### Using the HTTP Client Directly

```javascript
import { CarespaceClient } from '@carespace/sdk-js';

const client = new CarespaceClient({
  baseURL: 'https://api.carespace.ai',
  apiKey: 'your-api-key'
});

// Make custom requests
const response = await client.get('/custom-endpoint');
const data = await client.post('/custom-endpoint', { key: 'value' });
```

#### Custom Headers

```javascript
const carespace = new CarespaceAPI({
  apiKey: 'your-api-key',
  headers: {
    'X-Custom-Header': 'custom-value'
  }
});
```

## API Reference

### CarespaceAPI

The main API class that provides access to all endpoints.

#### Constructor Options

- `baseURL` (string): API base URL
- `apiKey` (string): API authentication key
- `timeout` (number): Request timeout in milliseconds
- `headers` (object): Additional headers to send with requests

#### Methods

- `setApiKey(apiKey)`: Update the API key
- `getClient()`: Get the underlying HTTP client

### Available APIs

- `auth`: Authentication endpoints
- `users`: User management
- `clients`: Client management
- `programs`: Program management

## Requirements

- Node.js 14+ (for ES6 module support)
- Modern browser with Fetch API support

## License

MIT