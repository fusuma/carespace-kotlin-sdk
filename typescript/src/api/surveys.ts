import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * Surveys API - Handles survey management and survey templates
 */
export class SurveysAPI extends BaseAPI {
  /**
   * Survey management
   */

  /** Create survey */
  async createSurvey(surveyData: RequestBodyType<PathItemMethod<paths['/survey']>>): Promise<ResponseType<PathItemMethod<paths['/survey']>>> {
    const response = await this.client.post('/survey', surveyData);
    return response.data;
  }

  /** Get all surveys by user */
  async getAllSurveysByUser(userId: string): Promise<ResponseType<PathItemMethod<paths['/survey/{userId}']>>> {
    const response = await this.client.get(`/survey/${userId}`);
    return response.data;
  }

  /** Update survey */
  async updateSurvey(surveyId: string, surveyData: RequestBodyType<PathItemMethod<paths['/survey/{surveyId}']>>): Promise<ResponseType<PathItemMethod<paths['/survey/{surveyId}']>>> {
    const response = await this.client.patch(`/survey/${surveyId}`, surveyData);
    return response.data;
  }

  /** Delete survey */
  async deleteSurvey(surveyId: string): Promise<ResponseType<PathItemMethod<paths['/survey/{surveyId}']>>> {
    const response = await this.client.delete(`/survey/${surveyId}`);
    return response.data;
  }

  /** Save survey result */
  async saveSurveyResult(surveyId: string, resultData: RequestBodyType<PathItemMethod<paths['/survey/session/{surveyId}']>>): Promise<ResponseType<PathItemMethod<paths['/survey/session/{surveyId}']>>> {
    const response = await this.client.post(`/survey/session/${surveyId}`, resultData);
    return response.data;
  }

  /** Get survey results by user */
  async getSurveyResultsByUser(userId: string): Promise<ResponseType<PathItemMethod<paths['/survey/session/{userId}']>>> {
    const response = await this.client.get(`/survey/session/${userId}`);
    return response.data;
  }

  /** Get survey by ID */
  async getSurveyById(surveyId: string): Promise<ResponseType<PathItemMethod<paths['/survey/get/{surveyId}']>>> {
    const response = await this.client.get(`/survey/get/${surveyId}`);
    return response.data;
  }

  /** Get survey result by ID */
  async getSurveyResultById(surveyResultId: string): Promise<ResponseType<PathItemMethod<paths['/survey/result/{surveyResultId}']>>> {
    const response = await this.client.get(`/survey/result/${surveyResultId}`);
    return response.data;
  }

  /** Get survey results by survey ID */
  async getSurveyResultsBySurveyId(surveyId: string): Promise<ResponseType<PathItemMethod<paths['/survey/sessions/{surveyId}']>>> {
    const response = await this.client.get(`/survey/sessions/${surveyId}`);
    return response.data;
  }

  /** Get survey sessions by status */
  async getSurveySessionsByStatus(status: string): Promise<ResponseType<PathItemMethod<paths['/survey/sessions/status/{status}/users']>>> {
    const response = await this.client.get(`/survey/sessions/status/${status}/users`);
    return response.data;
  }

  /** Update survey session status */
  async updateSurveySessionStatus(sessionId: string, statusData: RequestBodyType<PathItemMethod<paths['/survey/sessions/{sessionId}/status']>>): Promise<ResponseType<PathItemMethod<paths['/survey/sessions/{sessionId}/status']>>> {
    const response = await this.client.patch(`/survey/sessions/${sessionId}/status`, statusData);
    return response.data;
  }

  /**
   * Survey Template management
   */

  /** Create survey template */
  async createSurveyTemplate(templateData: RequestBodyType<PathItemMethod<paths['/survey/template']>>): Promise<ResponseType<PathItemMethod<paths['/survey/template']>>> {
    const response = await this.client.post('/survey/template', templateData);
    return response.data;
  }

  /** Get all survey templates by client */
  async getAllSurveyTemplatesByClient(): Promise<ResponseType<PathItemMethod<paths['/survey/template/list']>>> {
    const response = await this.client.get('/survey/template/list');
    return response.data;
  }

  /** Update survey template */
  async updateSurveyTemplate(surveyTemplateId: string, templateData: RequestBodyType<PathItemMethod<paths['/survey/template/{surveyTemplateId}']>>): Promise<ResponseType<PathItemMethod<paths['/survey/template/{surveyTemplateId}']>>> {
    const response = await this.client.patch(`/survey/template/${surveyTemplateId}`, templateData);
    return response.data;
  }
}