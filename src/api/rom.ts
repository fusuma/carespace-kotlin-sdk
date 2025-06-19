import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * ROM (Range of Motion) API
 * Handles ROM programs, sessions, results management, and ROM program templates
 */
export class ROMAPI extends BaseAPI {

  // ============================================================================
  // ROM (RANGE OF MOTION) ENDPOINTS
  // ============================================================================

  /**
   * Range of Motion programs, sessions, and results management
   */

  /** Get all ROM results for patient */
  async getAllPatientRomResults(patientId: string): Promise<ResponseType<PathItemMethod<paths['/rom/patients/{patientId}/results']>>> {
    const response = await this.client.get(`/rom/patients/${patientId}/results`);
    return response.data;
  }

  /** Get all ROM sessions for user */
  async getAllRomSessionsByUser(userId: string): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/{userId}/all']>>> {
    const response = await this.client.get(`/rom/sessions/${userId}/all`);
    return response.data;
  }

  /** Get last ROM session for user */
  async getLastUserRomSession(userId: string): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/{userId}']>>> {
    const response = await this.client.get(`/rom/sessions/${userId}`);
    return response.data;
  }

  /** Get all patient ROM results by sessions */
  async getAllPatientResultsBySession(patientId: string): Promise<ResponseType<PathItemMethod<paths['/rom/patients/{patientId}/results/sessions']>>> {
    const response = await this.client.get(`/rom/patients/${patientId}/results/sessions`);
    return response.data;
  }

  /** Get all ROM sessions by status */
  async getAllRomSessionsByStatus(status: string): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/status/{status}/users']>>> {
    const response = await this.client.get(`/rom/sessions/status/${status}/users`);
    return response.data;
  }

  /** Update ROM session status */
  async updateRomSessionStatus(sessionId: string, statusData: RequestBodyType<PathItemMethod<paths['/rom/sessions/{sessionId}/status']>>): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/{sessionId}/status']>>> {
    const response = await this.client.patch(`/rom/sessions/${sessionId}/status`, statusData);
    return response.data;
  }

  /** Get ROM session by ID */
  async getRomSessionById(sessionId: string): Promise<ResponseType<PathItemMethod<paths['/rom/session/{sessionId}']>>> {
    const response = await this.client.get(`/rom/session/${sessionId}`);
    return response.data;
  }

  /** Create ROM session */
  async createRomSession(sessionData: RequestBodyType<PathItemMethod<paths['/rom/sessions']>>): Promise<ResponseType<PathItemMethod<paths['/rom/sessions']>>> {
    const response = await this.client.post('/rom/sessions', sessionData);
    return response.data;
  }

  /** Get all sessions by ROM program */
  async getAllSessionsByRomProgram(programId: string): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/programs/{programId}']>>> {
    const response = await this.client.get(`/rom/sessions/programs/${programId}`);
    return response.data;
  }

  /** Update ROM session */
  async updateRomSession(sessionId: string, sessionData: RequestBodyType<PathItemMethod<paths['/rom/sessions/{sessionId}']>>): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/{sessionId}']>>> {
    const response = await this.client.patch(`/rom/sessions/${sessionId}`, sessionData);
    return response.data;
  }

  /** Complete ROM session */
  async completeRomSession(sessionId: string, completionData: RequestBodyType<PathItemMethod<paths['/rom/sessions/{sessionId}/complete']>>): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/{sessionId}/complete']>>> {
    const response = await this.client.patch(`/rom/sessions/${sessionId}/complete`, completionData);
    return response.data;
  }

  /** Save patient ROM results */
  async savePatientRomResults(resultsData: RequestBodyType<PathItemMethod<paths['/rom/sessions/patient-results']>>): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/patient-results']>>> {
    const response = await this.client.post('/rom/sessions/patient-results', resultsData);
    return response.data;
  }

  /** Update patient ROM results */
  async updatePatientRomResults(resultId: string, resultsData: RequestBodyType<PathItemMethod<paths['/rom/sessions/patient-results/{resultId}']>>): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/patient-results/{resultId}']>>> {
    const response = await this.client.patch(`/rom/sessions/patient-results/${resultId}`, resultsData);
    return response.data;
  }

  /** Get ROM library */
  async getRomLibrary(): Promise<ResponseType<PathItemMethod<paths['/rom/library']>>> {
    const response = await this.client.get('/rom/library');
    return response.data;
  }

  /** Create ROM library entry */
  async createRomLibrary(libraryData: RequestBodyType<PathItemMethod<paths['/rom/library']>>): Promise<ResponseType<PathItemMethod<paths['/rom/library']>>> {
    const response = await this.client.post('/rom/library', libraryData);
    return response.data;
  }

  /** Get ROM library by ID */
  async getRomLibraryById(libraryId: string): Promise<ResponseType<PathItemMethod<paths['/rom/library/{libraryId}']>>> {
    const response = await this.client.get(`/rom/library/${libraryId}`);
    return response.data;
  }

  /** Update ROM library */
  async updateRomLibrary(libraryId: string, libraryData: RequestBodyType<PathItemMethod<paths['/rom/library/{libraryId}']>>): Promise<ResponseType<PathItemMethod<paths['/rom/library/{libraryId}']>>> {
    const response = await this.client.patch(`/rom/library/${libraryId}`, libraryData);
    return response.data;
  }

  /** Delete ROM library */
  async deleteRomLibrary(libraryId: string): Promise<ResponseType<PathItemMethod<paths['/rom/library/{libraryId}']>>> {
    const response = await this.client.delete(`/rom/library/${libraryId}`);
    return response.data;
  }

  /** Get all ROM programs by user ID */
  async getAllRomProgramsByUserId(): Promise<ResponseType<PathItemMethod<paths['/rom/programs']>>> {
    const response = await this.client.get('/rom/programs');
    return response.data;
  }

  /** Create ROM program */
  async createRomProgram(programData: RequestBodyType<PathItemMethod<paths['/rom/programs']>>): Promise<ResponseType<PathItemMethod<paths['/rom/programs']>>> {
    const response = await this.client.post('/rom/programs', programData);
    return response.data;
  }

  /** Get all ROM programs by patient ID */
  async getAllRomProgramsByPatientId(patientId: string): Promise<ResponseType<PathItemMethod<paths['/rom/programs/patients/{patientId}']>>> {
    const response = await this.client.get(`/rom/programs/patients/${patientId}`);
    return response.data;
  }

  /** Get ROM program by ID */
  async getRomProgramById(programId: string): Promise<ResponseType<PathItemMethod<paths['/rom/programs/{programId}']>>> {
    const response = await this.client.get(`/rom/programs/${programId}`);
    return response.data;
  }

  /** Update ROM program */
  async updateRomProgram(programId: string, programData: RequestBodyType<PathItemMethod<paths['/rom/programs/{programId}']>>): Promise<ResponseType<PathItemMethod<paths['/rom/programs/{programId}']>>> {
    const response = await this.client.patch(`/rom/programs/${programId}`, programData);
    return response.data;
  }

  /** Delete ROM program */
  async deleteRomProgram(programId: string): Promise<ResponseType<PathItemMethod<paths['/rom/programs/{programId}']>>> {
    const response = await this.client.delete(`/rom/programs/${programId}`);
    return response.data;
  }

  /** Generate PDF for ROM session and save on Azure */
  async generateRomSessionPdf(sessionId: string, pdfData: RequestBodyType<PathItemMethod<paths['/rom/sessions/{sessionId}/pdf']>>): Promise<ResponseType<PathItemMethod<paths['/rom/sessions/{sessionId}/pdf']>>> {
    const response = await this.client.post(`/rom/sessions/${sessionId}/pdf`, pdfData);
    return response.data;
  }

  /** Get mobility scores for user */
  async getMobilityScores(userId: string): Promise<ResponseType<PathItemMethod<paths['/rom/mobility-score/{userId}']>>> {
    const response = await this.client.get(`/rom/mobility-score/${userId}`);
    return response.data;
  }

  // ============================================================================
  // ROM PROGRAM TEMPLATES ENDPOINTS
  // ============================================================================

  /**
   * ROM Program Templates management
   */

  /** Get all ROM templates by client */
  async getAllRomTemplatesByClient(): Promise<ResponseType<PathItemMethod<paths['/rom/program-templates']>>> {
    const response = await this.client.get('/rom/program-templates');
    return response.data;
  }

  /** Create ROM template */
  async createRomTemplate(templateData: RequestBodyType<PathItemMethod<paths['/rom/program-templates']>>): Promise<ResponseType<PathItemMethod<paths['/rom/program-templates']>>> {
    const response = await this.client.post('/rom/program-templates', templateData);
    return response.data;
  }

  /** Get ROM template by ID */
  async getRomTemplateById(programTemplateId: string): Promise<ResponseType<PathItemMethod<paths['/rom/program-templates/{programTemplateId}']>>> {
    const response = await this.client.get(`/rom/program-templates/${programTemplateId}`);
    return response.data;
  }

  /** Update ROM template */
  async updateRomTemplate(programTemplateId: string, templateData: RequestBodyType<PathItemMethod<paths['/rom/program-templates/{programTemplateId}']>>): Promise<ResponseType<PathItemMethod<paths['/rom/program-templates/{programTemplateId}']>>> {
    const response = await this.client.patch(`/rom/program-templates/${programTemplateId}`, templateData);
    return response.data;
  }

  /** Delete ROM template */
  async deleteRomTemplate(programTemplateId: string): Promise<ResponseType<PathItemMethod<paths['/rom/program-templates/{programTemplateId}']>>> {
    const response = await this.client.delete(`/rom/program-templates/${programTemplateId}`);
    return response.data;
  }
}