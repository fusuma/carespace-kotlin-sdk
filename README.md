# Carespace SDK

TypeScript SDK for the Carespace API.

## Installation

```bash
npm install carespace-sdk
```

## Usage

```typescript
import { CarespaceAPI } from 'carespace-sdk';

const api = new CarespaceAPI({
  baseURL: 'https://api-dev.carespace.ai',
  apiKey: 'your-api-key'
});

// Authentication
const loginResult = await api.signIn({
  email: 'user@example.com',
  password: 'password'
});

// Get all users
const users = await api.getAllUsers();

// Get user by ID
const user = await api.getUser('user-id');

// Create client
const client = await api.createClient({
  name: 'Client Name',
  // other client data
});
```

## Configuration

The SDK accepts the following configuration options:

- `baseURL`: API base URL (defaults to 'https://api-dev.carespace.ai')
- `apiKey`: Your API key for authentication
- `timeout`: Request timeout in milliseconds (defaults to 30000)

## API Methods

### Authentication
- `login()` - Login endpoint
- `logout()` - Logout endpoint  
- `signIn(credentials)` - Sign in with credentials
- `signOut()` - Sign out
- `register(userData)` - Register new user
- `refreshToken(refreshToken)` - Refresh access token
- `passwordRecovery(email)` - Password recovery

### Clients
- `getAllClients()` - Get all clients
- `createClient(clientData)` - Create new client
- `getClient(id)` - Get client by ID
- `updateClient(id, updates)` - Update client
- `deleteClient(id)` - Delete client
- `getClientByInviteCode(inviteCode)` - Get client by invite code

### Users
- `getAllUsers()` - Get all users
- `getUser(id)` - Get user by ID
- `updateUser(id, updates)` - Update user
- `deleteUser(id)` - Delete user
- `getUserProfile(id)` - Get user profile
- `getUsersSummary()` - Get users summary
- `getAllNewUsers()` - Get all new users
- `getAllPhysiotherapists()` - Get all physiotherapists
- `getAllUnassignedUsers()` - Get all unassigned users
- `getAllPendingInvites()` - Get all pending invites
- `getAllAssignedByPhysiotherapist(physiotherapistId)` - Get users assigned to physiotherapist
- `activateUser(userData)` - Activate user account
- `updateUserPassword(userId, passwordData)` - Update user password

## Development

```bash
# Install dependencies
npm install

# Generate types from swagger.json
npm run generate

# Build the SDK
npm run build

# Run in development mode
npm run dev
```