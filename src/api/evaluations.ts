import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * Evaluations API - Handles patient evaluation sessions management
 */
export class EvaluationsAPI extends BaseAPI {
  /**
   * Patient evaluation sessions management
   */

  /** Create evaluation session */
  async createEvaluationSession(evaluationData: RequestBodyType<PathItemMethod<paths['/evaluation']>>): Promise<ResponseType<PathItemMethod<paths['/evaluation']>>> {
    const response = await this.client.post('/evaluation', evaluationData);
    return response.data;
  }

  /** Update evaluation session */
  async updateEvaluationSession(sessionId: string, sessionData: RequestBodyType<PathItemMethod<paths['/evaluation/{sessionId}']>>): Promise<ResponseType<PathItemMethod<paths['/evaluation/{sessionId}']>>> {
    const response = await this.client.patch(`/evaluation/${sessionId}`, sessionData);
    return response.data;
  }

  /** Get evaluation sessions by user ID */
  async getEvaluationSessionsByUserId(userId: string): Promise<ResponseType<PathItemMethod<paths['/evaluation/{userId}']>>> {
    const response = await this.client.get(`/evaluation/${userId}`);
    return response.data;
  }

  /** Get evaluation session by ID */
  async getEvaluationSessionById(evaluationId: string): Promise<ResponseType<PathItemMethod<paths['/evaluation/sessions/{evaluationId}']>>> {
    const response = await this.client.get(`/evaluation/sessions/${evaluationId}`);
    return response.data;
  }

  /** Get evaluation results by status */
  async getEvaluationResultsByStatus(status: string): Promise<ResponseType<PathItemMethod<paths['/evaluation/sessions/status/{status}/users']>>> {
    const response = await this.client.get(`/evaluation/sessions/status/${status}/users`);
    return response.data;
  }

  /** Update evaluation session status */
  async updateEvaluationSessionStatus(sessionId: string, statusData: RequestBodyType<PathItemMethod<paths['/evaluation/sessions/{sessionId}/status']>>): Promise<ResponseType<PathItemMethod<paths['/evaluation/sessions/{sessionId}/status']>>> {
    const response = await this.client.patch(`/evaluation/sessions/${sessionId}/status`, statusData);
    return response.data;
  }
}