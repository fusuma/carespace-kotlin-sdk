import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * Posture API - Handles posture analytics and reporting
 */
export class PostureAPI extends BaseAPI {
  /**
   * Posture analytics and reporting
   */

  /** Create posture analytics session */
  async createPostureAnalyticsSession(sessionData: RequestBodyType<PathItemMethod<paths['/posture-analytics/sessions']>>): Promise<ResponseType<PathItemMethod<paths['/posture-analytics/sessions']>>> {
    const response = await this.client.post('/posture-analytics/sessions', sessionData);
    return response.data;
  }

  /** Create posture analytics */
  async createPostureAnalytics(analyticsData: RequestBodyType<PathItemMethod<paths['/posture-analytics']>>): Promise<ResponseType<PathItemMethod<paths['/posture-analytics']>>> {
    const response = await this.client.post('/posture-analytics', analyticsData);
    return response.data;
  }

  /** Get posture analytics sessions by user ID */
  async getPostureAnalyticsSessionsByUserId(userId: string): Promise<ResponseType<PathItemMethod<paths['/posture-analytics/sessions/users/{userId}']>>> {
    const response = await this.client.get(`/posture-analytics/sessions/users/${userId}`);
    return response.data;
  }

  /** Get posture analytics by ID */
  async getPostureAnalyticsById(id: string): Promise<ResponseType<PathItemMethod<paths['/posture-analytics/{id}']>>> {
    const response = await this.client.get(`/posture-analytics/${id}`);
    return response.data;
  }

  /** Delete posture analytics session by ID */
  async deletePostureAnalyticsSessionById(id: string): Promise<ResponseType<PathItemMethod<paths['/posture-analytics/sessions/{id}']>>> {
    const response = await this.client.delete(`/posture-analytics/sessions/${id}`);
    return response.data;
  }

  /** Update posture session status */
  async updatePostureSessionStatus(sessionId: string, statusData: RequestBodyType<PathItemMethod<paths['/posture-analytics/sessions/{sessionId}/status']>>): Promise<ResponseType<PathItemMethod<paths['/posture-analytics/sessions/{sessionId}/status']>>> {
    const response = await this.client.patch(`/posture-analytics/sessions/${sessionId}/status`, statusData);
    return response.data;
  }

  /** Create posture analytics report */
  async createPostureAnalyticsReport(reportData: RequestBodyType<PathItemMethod<paths['/posture-analytics/report']>>): Promise<ResponseType<PathItemMethod<paths['/posture-analytics/report']>>> {
    const response = await this.client.post('/posture-analytics/report', reportData);
    return response.data;
  }

  /** Get posture analytics report by user ID */
  async getPostureAnalyticsReportByUserId(userId: string): Promise<ResponseType<PathItemMethod<paths['/posture-analytics/users/{userId}/report']>>> {
    const response = await this.client.get(`/posture-analytics/users/${userId}/report`);
    return response.data;
  }

  /** Get posture analytics report by session ID */
  async getPostureAnalyticsReportBySessionId(sessionId: string): Promise<ResponseType<PathItemMethod<paths['/posture-analytics/sessions/{sessionId}/report']>>> {
    const response = await this.client.get(`/posture-analytics/sessions/${sessionId}/report`);
    return response.data;
  }
}