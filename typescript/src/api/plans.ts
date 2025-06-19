import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * Plans API - Handles plans and subscriptions management
 */
export class PlansAPI extends BaseAPI {
  /**
   * Plans and subscriptions management
   */

  /** Get users plans */
  async getUsersPlans(): Promise<ResponseType<PathItemMethod<paths['/plans/users']>>> {
    const response = await this.client.get('/plans/users');
    return response.data;
  }

  /** Get user plan */
  async getUserPlan(userId: string): Promise<ResponseType<PathItemMethod<paths['/plans/users/{userId}']>>> {
    const response = await this.client.get(`/plans/users/${userId}`);
    return response.data;
  }

  /** Create user plan */
  async createUserPlan(userId: string, planData: RequestBodyType<PathItemMethod<paths['/plans/users/{userId}']>>): Promise<ResponseType<PathItemMethod<paths['/plans/users/{userId}']>>> {
    const response = await this.client.post(`/plans/users/${userId}`, planData);
    return response.data;
  }

  /** Update user plan */
  async updateUserPlan(userId: string, planData: RequestBodyType<PathItemMethod<paths['/plans/users/{userId}']>>): Promise<ResponseType<PathItemMethod<paths['/plans/users/{userId}']>>> {
    const response = await this.client.patch(`/plans/users/${userId}`, planData);
    return response.data;
  }
}