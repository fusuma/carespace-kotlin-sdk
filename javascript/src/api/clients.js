import { BaseAPI } from './base.js';

/**
 * Clients API endpoints
 */
export class ClientsAPI extends BaseAPI {
  async getClients(params = {}) {
    return this.get('/clients', {}, params);
  }

  async getClient(clientId) {
    return this.get('/clients/{id}', { id: clientId });
  }

  async createClient(clientData) {
    return this.post('/clients', clientData);
  }

  async updateClient(clientId, clientData) {
    return this.put('/clients/{id}', clientData, { id: clientId });
  }

  async deleteClient(clientId) {
    return this.delete('/clients/{id}', { id: clientId });
  }

  async getClientStats(clientId) {
    return this.get('/clients/{id}/stats', { id: clientId });
  }

  async getClientPrograms(clientId, params = {}) {
    return this.get('/clients/{id}/programs', { id: clientId }, params);
  }

  async assignProgramToClient(clientId, programId, assignmentData = {}) {
    return this.post('/clients/{id}/programs/{programId}', assignmentData, {
      id: clientId,
      programId
    });
  }

  async removeClientProgram(clientId, programId) {
    return this.delete('/clients/{id}/programs/{programId}', {
      id: clientId,
      programId
    });
  }

  async getClientEvaluations(clientId, params = {}) {
    return this.get('/clients/{id}/evaluations', { id: clientId }, params);
  }

  async getClientReports(clientId, params = {}) {
    return this.get('/clients/{id}/reports', { id: clientId }, params);
  }
}