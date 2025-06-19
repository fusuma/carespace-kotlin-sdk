import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

export class ClientsAPI extends BaseAPI {
  /**
   * Client management endpoints for multi-tenant functionality
   */

  /** Get all clients */
  async getAllClients(): Promise<ResponseType<PathItemMethod<paths['/clients']>>> {
    const response = await this.client.get('/clients');
    return response.data;
  }

  /** Create new client */
  async createClient(clientData: RequestBodyType<PathItemMethod<paths['/clients']>>): Promise<ResponseType<PathItemMethod<paths['/clients']>>> {
    const response = await this.client.post('/clients', clientData);
    return response.data;
  }

  /** Get client by ID */
  async getClient(id: string): Promise<ResponseType<PathItemMethod<paths['/clients/{id}']>>> {
    const response = await this.client.get(`/clients/${id}`);
    return response.data;
  }

  /** Update client */
  async updateClient(id: string, updates: RequestBodyType<PathItemMethod<paths['/clients/{id}']>>): Promise<ResponseType<PathItemMethod<paths['/clients/{id}']>>> {
    const response = await this.client.patch(`/clients/${id}`, updates);
    return response.data;
  }

  /** Delete client */
  async deleteClient(id: string): Promise<ResponseType<PathItemMethod<paths['/clients/{id}']>>> {
    const response = await this.client.delete(`/clients/${id}`);
    return response.data;
  }

  /** Get client by invite code */
  async getClientByInviteCode(inviteCode: string): Promise<ResponseType<PathItemMethod<paths['/clients/invite-code/{inviteCode}']>>> {
    const response = await this.client.get(`/clients/invite-code/${inviteCode}`);
    return response.data;
  }
}