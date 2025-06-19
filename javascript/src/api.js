import { CarespaceClient } from './client.js';
import { AuthAPI } from './api/auth.js';
import { UsersAPI } from './api/users.js';
import { ClientsAPI } from './api/clients.js';
import { ProgramsAPI } from './api/programs.js';

/**
 * Main Carespace API class
 */
export class CarespaceAPI {
  constructor(config = {}) {
    this.client = new CarespaceClient(config);
    
    // Initialize API endpoints
    this.auth = new AuthAPI(this.client);
    this.users = new UsersAPI(this.client);
    this.clients = new ClientsAPI(this.client);
    this.programs = new ProgramsAPI(this.client);
  }

  setApiKey(apiKey) {
    this.client.setApiKey(apiKey);
  }

  getClient() {
    return this.client;
  }
}