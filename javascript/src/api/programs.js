import { BaseAPI } from './base.js';

/**
 * Programs API endpoints
 */
export class ProgramsAPI extends BaseAPI {
  async getPrograms(params = {}) {
    return this.get('/programs', {}, params);
  }

  async getProgram(programId) {
    return this.get('/programs/{id}', { id: programId });
  }

  async createProgram(programData) {
    return this.post('/programs', programData);
  }

  async updateProgram(programId, programData) {
    return this.put('/programs/{id}', programData, { id: programId });
  }

  async deleteProgram(programId) {
    return this.delete('/programs/{id}', { id: programId });
  }

  async getProgramExercises(programId, params = {}) {
    return this.get('/programs/{id}/exercises', { id: programId }, params);
  }

  async addExerciseToProgram(programId, exerciseData) {
    return this.post('/programs/{id}/exercises', exerciseData, { id: programId });
  }

  async updateProgramExercise(programId, exerciseId, exerciseData) {
    return this.put('/programs/{id}/exercises/{exerciseId}', exerciseData, {
      id: programId,
      exerciseId
    });
  }

  async removeProgramExercise(programId, exerciseId) {
    return this.delete('/programs/{id}/exercises/{exerciseId}', {
      id: programId,
      exerciseId
    });
  }

  async duplicateProgram(programId, duplicateData = {}) {
    return this.post('/programs/{id}/duplicate', duplicateData, { id: programId });
  }

  async getProgramTemplates(params = {}) {
    return this.get('/programs/templates', {}, params);
  }
}