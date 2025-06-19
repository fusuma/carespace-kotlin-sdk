import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * AuthAPI class provides authentication and authorization related endpoints.
 * This includes user login, logout, token management, registration, and password recovery functionality.
 */
export class AuthAPI extends BaseAPI {
  
  // ============================================================================
  // AUTH ENDPOINTS
  // ============================================================================

  /**
   * Authentication and authorization related methods
   */

  /** 
   * Login endpoint
   * @returns Promise with login response data
   */
  async login(): Promise<ResponseType<PathItemMethod<paths['/auth/login']>>> {
    const response = await this.client.get('/auth/login');
    return response.data;
  }

  /** 
   * Logout endpoint
   * @returns Promise with logout response data
   */
  async logout(): Promise<ResponseType<PathItemMethod<paths['/auth/logout']>>> {
    const response = await this.client.get('/auth/logout');
    return response.data;
  }

  /** 
   * Generate authentication token
   * @returns Promise with token generation response data
   */
  async generateToken(): Promise<ResponseType<PathItemMethod<paths['/auth/token']>>> {
    const response = await this.client.post('/auth/token');
    return response.data;
  }

  /** 
   * Sign in with credentials
   * @param credentials - User credentials for sign in
   * @returns Promise with sign in response data
   */
  async signIn(credentials: RequestBodyType<PathItemMethod<paths['/auth/sign-in']>>): Promise<ResponseType<PathItemMethod<paths['/auth/sign-in']>>> {
    const response = await this.client.post('/auth/sign-in', credentials);
    return response.data;
  }

  /** 
   * Refresh authentication token
   * @param tokenData - Token refresh data
   * @returns Promise with token refresh response data
   */
  async refreshToken(tokenData: RequestBodyType<PathItemMethod<paths['/auth/refresh-token']>>): Promise<ResponseType<PathItemMethod<paths['/auth/refresh-token']>>> {
    const response = await this.client.post('/auth/refresh-token', tokenData);
    return response.data;
  }

  /** 
   * Register new user
   * @param userData - New user registration data
   * @returns Promise with registration response data
   */
  async register(userData: RequestBodyType<PathItemMethod<paths['/auth/register']>>): Promise<ResponseType<PathItemMethod<paths['/auth/register']>>> {
    const response = await this.client.post('/auth/register', userData);
    return response.data;
  }

  /** 
   * Sign out user
   * @returns Promise with sign out response data
   */
  async signOut(): Promise<ResponseType<PathItemMethod<paths['/auth/sign-out']>>> {
    const response = await this.client.post('/auth/sign-out');
    return response.data;
  }

  /** 
   * Password recovery
   * @param recoveryData - Password recovery data
   * @returns Promise with password recovery response data
   */
  async passwordRecovery(recoveryData: RequestBodyType<PathItemMethod<paths['/auth/password-recovery']>>): Promise<ResponseType<PathItemMethod<paths['/auth/password-recovery']>>> {
    const response = await this.client.post('/auth/password-recovery', recoveryData);
    return response.data;
  }
}