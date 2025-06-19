import { BaseAPI } from './base.js';

/**
 * Users API endpoints
 */
export class UsersAPI extends BaseAPI {
  async getUsers(params = {}) {
    return this.get('/users', {}, params);
  }

  async getUser(userId) {
    return this.get('/users/{id}', { id: userId });
  }

  async createUser(userData) {
    return this.post('/users', userData);
  }

  async updateUser(userId, userData) {
    return this.put('/users/{id}', userData, { id: userId });
  }

  async deleteUser(userId) {
    return this.delete('/users/{id}', { id: userId });
  }

  async getUserProfile() {
    return this.get('/users/profile');
  }

  async updateUserProfile(profileData) {
    return this.put('/users/profile', profileData);
  }

  async getUserSettings(userId) {
    return this.get('/users/{id}/settings', { id: userId });
  }

  async updateUserSettings(userId, settings) {
    return this.put('/users/{id}/settings', settings, { id: userId });
  }

  async getUserPreferences(userId) {
    return this.get('/users/{id}/preferences', { id: userId });
  }

  async updateUserPreferences(userId, preferences) {
    return this.put('/users/{id}/preferences', preferences, { id: userId });
  }
}