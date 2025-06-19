import { BaseAPI } from './base.js';

/**
 * Authentication API endpoints
 */
export class AuthAPI extends BaseAPI {
  async login(credentials) {
    return this.post('/auth/login', credentials);
  }

  async logout() {
    return this.post('/auth/logout');
  }

  async refreshToken(refreshToken) {
    return this.post('/auth/refresh', { refresh_token: refreshToken });
  }

  async forgotPassword(email) {
    return this.post('/auth/forgot-password', { email });
  }

  async resetPassword(token, password) {
    return this.post('/auth/reset-password', { token, password });
  }

  async changePassword(currentPassword, newPassword) {
    return this.post('/auth/change-password', {
      current_password: currentPassword,
      new_password: newPassword
    });
  }

  async verifyEmail(token) {
    return this.post('/auth/verify-email', { token });
  }

  async resendVerification(email) {
    return this.post('/auth/resend-verification', { email });
  }
}