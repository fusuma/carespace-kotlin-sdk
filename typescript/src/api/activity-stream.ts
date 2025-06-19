import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * ActivityStreamAPI class provides activity stream related endpoints.
 * This includes patient-physiotherapist communication, feedback, evaluations, and posts.
 */
export class ActivityStreamAPI extends BaseAPI {
  
  // ============================================================================
  // ACTIVITY STREAM ENDPOINTS
  // ============================================================================

  /**
   * Activity Stream features for patient-physiotherapist communication
   */

  /** Get unread activity stream history */
  async getActivityStreamHistoryUnread(): Promise<ResponseType<PathItemMethod<paths['/activity-stream/history/unread']>>> {
    const response = await this.client.get('/activity-stream/history/unread');
    return response.data;
  }

  /** Get all activity stream histories for user */
  async getAllActivityStreamHistories(userId: string): Promise<ResponseType<PathItemMethod<paths['/activity-stream/history/{userId}']>>> {
    const response = await this.client.get(`/activity-stream/history/${userId}`);
    return response.data;
  }

  /** Mark activity stream history as read */
  async markActivityStreamHistoryAsRead(userId: string, readData: RequestBodyType<PathItemMethod<paths['/activity-stream/history/read/{userId}']>>): Promise<ResponseType<PathItemMethod<paths['/activity-stream/history/read/{userId}']>>> {
    const response = await this.client.patch(`/activity-stream/history/read/${userId}`, readData);
    return response.data;
  }

  /** Get activity stream by ID */
  async getActivityStreamById(id: string): Promise<ResponseType<PathItemMethod<paths['/activity-stream/{id}']>>> {
    const response = await this.client.get(`/activity-stream/${id}`);
    return response.data;
  }

  /** Create activity stream evaluation */
  async createActivityStreamEvaluation(evaluationData: RequestBodyType<PathItemMethod<paths['/activity-stream/evaluation']>>): Promise<ResponseType<PathItemMethod<paths['/activity-stream/evaluation']>>> {
    const response = await this.client.post('/activity-stream/evaluation', evaluationData);
    return response.data;
  }

  /** Get activity stream evaluation by ID */
  async getActivityStreamEvaluationById(id: string): Promise<ResponseType<PathItemMethod<paths['/activity-stream/evaluation/{id}']>>> {
    const response = await this.client.get(`/activity-stream/evaluation/${id}`);
    return response.data;
  }

  /** Create activity stream feedback */
  async createActivityStreamFeedback(feedbackData: RequestBodyType<PathItemMethod<paths['/activity-stream/feedback']>>): Promise<ResponseType<PathItemMethod<paths['/activity-stream/feedback']>>> {
    const response = await this.client.post('/activity-stream/feedback', feedbackData);
    return response.data;
  }

  /** Get activity stream feedback by ID */
  async getActivityStreamFeedbackById(id: string): Promise<ResponseType<PathItemMethod<paths['/activity-stream/feedback/{id}']>>> {
    const response = await this.client.get(`/activity-stream/feedback/${id}`);
    return response.data;
  }

  /** Create activity stream post */
  async createActivityStreamPost(postData: RequestBodyType<PathItemMethod<paths['/activity-stream/post']>>): Promise<ResponseType<PathItemMethod<paths['/activity-stream/post']>>> {
    const response = await this.client.post('/activity-stream/post', postData);
    return response.data;
  }

  /** Get activity stream post by ID */
  async getActivityStreamPostById(id: string): Promise<ResponseType<PathItemMethod<paths['/activity-stream/post/{id}']>>> {
    const response = await this.client.get(`/activity-stream/post/${id}`);
    return response.data;
  }
}