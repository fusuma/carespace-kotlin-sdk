import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

export class ProgramsAPI extends BaseAPI {
  // ============================================================================
  // EXERCISE PROGRAM ENDPOINTS
  // ============================================================================

  /**
   * Exercise Programs and AI-assisted program generation
   */

  /** Generate AI-assisted program */
  async generateAIProgram(programData: RequestBodyType<PathItemMethod<paths['/program/generate']>>): Promise<ResponseType<PathItemMethod<paths['/program/generate']>>> {
    const response = await this.client.post('/program/generate', programData);
    return response.data;
  }

  /** Create exercise program */
  async createExerciseProgram(programData: RequestBodyType<PathItemMethod<paths['/program']>>): Promise<ResponseType<PathItemMethod<paths['/program']>>> {
    const response = await this.client.post('/program', programData);
    return response.data;
  }

  /** Get all exercise programs by user */
  async getAllExerciseProgramsByUser(userId: string): Promise<ResponseType<PathItemMethod<paths['/program/{userId}']>>> {
    const response = await this.client.get(`/program/${userId}`);
    return response.data;
  }

  /** Update program exercise */
  async updateProgramExercise(exerciseId: string, exerciseData: RequestBodyType<PathItemMethod<paths['/program/exercises/{exerciseId}']>>): Promise<ResponseType<PathItemMethod<paths['/program/exercises/{exerciseId}']>>> {
    const response = await this.client.patch(`/program/exercises/${exerciseId}`, exerciseData);
    return response.data;
  }

  /** Update program */
  async updateExerciseProgram(programId: string, programData: RequestBodyType<PathItemMethod<paths['/program/{programId}']>>): Promise<ResponseType<PathItemMethod<paths['/program/{programId}']>>> {
    const response = await this.client.patch(`/program/${programId}`, programData);
    return response.data;
  }

  /** Add exercise to program */
  async addExerciseToProgram(programId: string, exerciseData: RequestBodyType<PathItemMethod<paths['/program/{programId}/exercises']>>): Promise<ResponseType<PathItemMethod<paths['/program/{programId}/exercises']>>> {
    const response = await this.client.post(`/program/${programId}/exercises`, exerciseData);
    return response.data;
  }

  /** Update program exercises order */
  async updateProgramExercisesOrder(programId: string, orderData: RequestBodyType<PathItemMethod<paths['/program/{programId}/exercises/order']>>): Promise<ResponseType<PathItemMethod<paths['/program/{programId}/exercises/order']>>> {
    const response = await this.client.patch(`/program/${programId}/exercises/order`, orderData);
    return response.data;
  }

  /** Create program from template */
  async createProgramFromTemplate(templateId: string, userId: string): Promise<ResponseType<PathItemMethod<paths['/program/templates/{templateId}/users/{userId}']>>> {
    const response = await this.client.post(`/program/templates/${templateId}/users/${userId}`);
    return response.data;
  }

  /** Create program session */
  async createProgramSession(sessionData: RequestBodyType<PathItemMethod<paths['/program/sessions']>>): Promise<ResponseType<PathItemMethod<paths['/program/sessions']>>> {
    const response = await this.client.post('/program/sessions', sessionData);
    return response.data;
  }

  /** Create program session result */
  async createProgramSessionResult(resultData: RequestBodyType<PathItemMethod<paths['/program/sessions/results']>>): Promise<ResponseType<PathItemMethod<paths['/program/sessions/results']>>> {
    const response = await this.client.post('/program/sessions/results', resultData);
    return response.data;
  }

  /** Get all sessions by program */
  async getAllSessionsByProgram(programId: string): Promise<ResponseType<PathItemMethod<paths['/program/{programId}/sessions']>>> {
    const response = await this.client.get(`/program/${programId}/sessions`);
    return response.data;
  }

  /** Get all exercise results by program */
  async getAllExerciseResultsByProgram(programId: string, exerciseId: string): Promise<ResponseType<PathItemMethod<paths['/program/{programId}/exercises/{exerciseId}/sessions']>>> {
    const response = await this.client.get(`/program/${programId}/exercises/${exerciseId}/sessions`);
    return response.data;
  }

  /** Create exercise library entry */
  async createExerciseLibraryEntry(libraryData: RequestBodyType<PathItemMethod<paths['/program/exercises/library']>>): Promise<ResponseType<PathItemMethod<paths['/program/exercises/library']>>> {
    const response = await this.client.post('/program/exercises/library', libraryData);
    return response.data;
  }

  /** Get exercises library by physiotherapist */
  async getExercisesLibraryByPhysiotherapist(physiotherapistId: string): Promise<ResponseType<PathItemMethod<paths['/program/exercises/library/physioterapists/{physioterapistId}']>>> {
    const response = await this.client.get(`/program/exercises/library/physioterapists/${physiotherapistId}`);
    return response.data;
  }

  /** Update exercise library */
  async updateExerciseLibrary(libraryData: RequestBodyType<PathItemMethod<paths['/program/library/exercises']>>): Promise<ResponseType<PathItemMethod<paths['/program/library/exercises']>>> {
    const response = await this.client.patch('/program/library/exercises', libraryData);
    return response.data;
  }

  /** Delete exercise from library */
  async deleteExerciseFromLibrary(exerciseId: string): Promise<ResponseType<PathItemMethod<paths['/program/exercises/library/{exerciseId}']>>> {
    const response = await this.client.delete(`/program/exercises/library/${exerciseId}`);
    return response.data;
  }

  /** Get program sessions by status */
  async getProgramSessionsByStatus(status: string): Promise<ResponseType<PathItemMethod<paths['/program/sessions/status/{status}/users']>>> {
    const response = await this.client.get(`/program/sessions/status/${status}/users`);
    return response.data;
  }

  /** Update program session status */
  async updateProgramSessionStatus(sessionId: string, statusData: RequestBodyType<PathItemMethod<paths['/program/sessions/{sessionId}/status']>>): Promise<ResponseType<PathItemMethod<paths['/program/sessions/{sessionId}/status']>>> {
    const response = await this.client.patch(`/program/sessions/${sessionId}/status`, statusData);
    return response.data;
  }

  /** Request program enrollment */
  async requestProgramEnrollment(userId: string, enrollmentData: RequestBodyType<PathItemMethod<paths['/program/enrollments/{userId}']>>): Promise<ResponseType<PathItemMethod<paths['/program/enrollments/{userId}']>>> {
    const response = await this.client.post(`/program/enrollments/${userId}`, enrollmentData);
    return response.data;
  }

  /** Get requested program enrollments */
  async getRequestedProgramEnrollments(): Promise<ResponseType<PathItemMethod<paths['/program/enrollments']>>> {
    const response = await this.client.get('/program/enrollments');
    return response.data;
  }

  /** Complete program session */
  async completeProgramSession(sessionId: string, completionData: RequestBodyType<PathItemMethod<paths['/program/sessions/{sessionId}/complete']>>): Promise<ResponseType<PathItemMethod<paths['/program/sessions/{sessionId}/complete']>>> {
    const response = await this.client.patch(`/program/sessions/${sessionId}/complete`, completionData);
    return response.data;
  }

  /** Get program session by ID */
  async getProgramSessionById(sessionId: string): Promise<ResponseType<PathItemMethod<paths['/program/sessions/{sessionId}']>>> {
    const response = await this.client.get(`/program/sessions/${sessionId}`);
    return response.data;
  }

  /** Get program by ID */
  async getProgramById(programId: string): Promise<ResponseType<PathItemMethod<paths['/program/get/{programId}']>>> {
    const response = await this.client.get(`/program/get/${programId}`);
    return response.data;
  }

  /** Delete program from user */
  async deleteProgramFromUser(programId: string, userId: string): Promise<ResponseType<PathItemMethod<paths['/program/{programId}/users/{userId}']>>> {
    const response = await this.client.delete(`/program/${programId}/users/${userId}`);
    return response.data;
  }

  /** Update program session result */
  async updateProgramSessionResult(sessionId: string, resultId: string, resultData: RequestBodyType<PathItemMethod<paths['/program/sessions/{sessionId}/results/{resultId}']>>): Promise<ResponseType<PathItemMethod<paths['/program/sessions/{sessionId}/results/{resultId}']>>> {
    const response = await this.client.patch(`/program/sessions/${sessionId}/results/${resultId}`, resultData);
    return response.data;
  }

  // ============================================================================
  // AI PROGRAM ENDPOINTS
  // ============================================================================

  /**
   * AI-assisted program generation and management
   */

  /** Get AI program data for user */
  async getAIProgramDataForUser(userId: string): Promise<ResponseType<PathItemMethod<paths['/program/open-ai/users/{userId}']>>> {
    const response = await this.client.get(`/program/open-ai/users/${userId}`);
    return response.data;
  }

  /** Generate AI program */
  async generateAIProgramData(programData: RequestBodyType<PathItemMethod<paths['/program/open-ai']>>): Promise<ResponseType<PathItemMethod<paths['/program/open-ai']>>> {
    const response = await this.client.post('/program/open-ai', programData);
    return response.data;
  }

  // ============================================================================
  // PROGRAM TEMPLATE ENDPOINTS
  // ============================================================================

  /**
   * Program Templates management
   */

  /** Create program template */
  async createProgramTemplate(templateData: RequestBodyType<PathItemMethod<paths['/program/program-template']>>): Promise<ResponseType<PathItemMethod<paths['/program/program-template']>>> {
    const response = await this.client.post('/program/program-template', templateData);
    return response.data;
  }

  /** Get all program templates by client */
  async getAllProgramTemplatesByClient(): Promise<ResponseType<PathItemMethod<paths['/program/program-template/list']>>> {
    const response = await this.client.get('/program/program-template/list');
    return response.data;
  }

  /** Update program template */
  async updateProgramTemplate(programId: string, templateData: RequestBodyType<PathItemMethod<paths['/program/program-template/{programId}']>>): Promise<ResponseType<PathItemMethod<paths['/program/program-template/{programId}']>>> {
    const response = await this.client.patch(`/program/program-template/${programId}`, templateData);
    return response.data;
  }

  /** Add exercise to program template */
  async addExerciseToProgramTemplate(programId: string, exerciseData: RequestBodyType<PathItemMethod<paths['/program/program-template/{programId}/exercises']>>): Promise<ResponseType<PathItemMethod<paths['/program/program-template/{programId}/exercises']>>> {
    const response = await this.client.post(`/program/program-template/${programId}/exercises`, exerciseData);
    return response.data;
  }

  /** Update program template exercise */
  async updateProgramTemplateExercise(exerciseId: string, exerciseData: RequestBodyType<PathItemMethod<paths['/program/program-template/exercises/{exerciseId}']>>): Promise<ResponseType<PathItemMethod<paths['/program/program-template/exercises/{exerciseId}']>>> {
    const response = await this.client.patch(`/program/program-template/exercises/${exerciseId}`, exerciseData);
    return response.data;
  }
}