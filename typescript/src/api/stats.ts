import { BaseAPI, PathItemMethod, ResponseType } from './base';
import { paths } from '../types';

/**
 * Stats API - Handles statistics and analytics
 */
export class StatsAPI extends BaseAPI {
  /**
   * Statistics and analytics
   */

  /** Get stats from client */
  async getStatsFromClient(): Promise<ResponseType<PathItemMethod<paths['/stats']>>> {
    const response = await this.client.get('/stats');
    return response.data;
  }
}