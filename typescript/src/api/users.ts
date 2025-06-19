import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * User and patient management API endpoints
 */
export class UsersAPI extends BaseAPI {
  
  // ============================================================================
  // USER/PATIENT MANAGEMENT ENDPOINTS
  // ============================================================================

  /**
   * User and patient management endpoints
   */

  /** Get users summary */
  async getUsersSummary(): Promise<ResponseType<PathItemMethod<paths['/users/summary']>>> {
    const response = await this.client.get('/users/summary');
    return response.data;
  }

  /** Get all new users */
  async getAllNewUsers(): Promise<ResponseType<PathItemMethod<paths['/users/new']>>> {
    const response = await this.client.get('/users/new');
    return response.data;
  }

  /** Get all physiotherapists */
  async getAllPhysiotherapists(): Promise<ResponseType<PathItemMethod<paths['/users/physiotherapists']>>> {
    const response = await this.client.get('/users/physiotherapists');
    return response.data;
  }

  /** Get all unassigned users */
  async getAllUnassignedUsers(): Promise<ResponseType<PathItemMethod<paths['/users/unassigned']>>> {
    const response = await this.client.get('/users/unassigned');
    return response.data;
  }

  /** Get all pending invites */
  async getAllPendingInvites(): Promise<ResponseType<PathItemMethod<paths['/users/pending-invites']>>> {
    const response = await this.client.get('/users/pending-invites');
    return response.data;
  }

  /** Get all users assigned to a physiotherapist */
  async getAllAssignedByPhysiotherapist(physiotherapistId: string): Promise<ResponseType<PathItemMethod<paths['/users/assigned/{physiotherapistId}']>>> {
    const response = await this.client.get(`/users/assigned/${physiotherapistId}`);
    return response.data;
  }

  /** Get all users */
  async getAllUsers(): Promise<ResponseType<PathItemMethod<paths['/users']>>> {
    const response = await this.client.get('/users');
    return response.data;
  }

  /** Get user by ID */
  async getUser(id: string): Promise<ResponseType<PathItemMethod<paths['/users/{id}']>>> {
    const response = await this.client.get(`/users/${id}`);
    return response.data;
  }

  /** Update user */
  async updateUser(id: string, updates: RequestBodyType<PathItemMethod<paths['/users/{id}']>>): Promise<ResponseType<PathItemMethod<paths['/users/{id}']>>> {
    const response = await this.client.patch(`/users/${id}`, updates);
    return response.data;
  }

  /** Delete user */
  async deleteUser(id: string): Promise<ResponseType<PathItemMethod<paths['/users/{id}']>>> {
    const response = await this.client.delete(`/users/${id}`);
    return response.data;
  }

  /** Get user profile */
  async getUserProfile(id: string): Promise<ResponseType<PathItemMethod<paths['/users/{id}/profile']>>> {
    const response = await this.client.get(`/users/${id}/profile`);
    return response.data;
  }

  /** Activate user */
  async activateUser(userData: RequestBodyType<PathItemMethod<paths['/users/activate']>>): Promise<ResponseType<PathItemMethod<paths['/users/activate']>>> {
    const response = await this.client.post('/users/activate', userData);
    return response.data;
  }

  /** Update user password */
  async updateUserPassword(userId: string, passwordData: RequestBodyType<PathItemMethod<paths['/users/{userId}/update-password']>>): Promise<ResponseType<PathItemMethod<paths['/users/{userId}/update-password']>>> {
    const response = await this.client.patch(`/users/${userId}/update-password`, passwordData);
    return response.data;
  }

  /** Associate patient with physiotherapist */
  async associatePatientPhysiotherapist(associationData: RequestBodyType<PathItemMethod<paths['/users/associate-patient-physiotherapist']>>): Promise<ResponseType<PathItemMethod<paths['/users/associate-patient-physiotherapist']>>> {
    const response = await this.client.post('/users/associate-patient-physiotherapist', associationData);
    return response.data;
  }

  /** Get physiotherapists for patient */
  async getPhysiotherapistsForPatient(patientId: string): Promise<ResponseType<PathItemMethod<paths['/users/patients/{patientId}/physiotherapists']>>> {
    const response = await this.client.get(`/users/patients/${patientId}/physiotherapists`);
    return response.data;
  }

  /** Get patients for physiotherapist */
  async getPatientsForPhysiotherapist(physiotherapistId: string): Promise<ResponseType<PathItemMethod<paths['/users/physiotherapists/{physiotherapistId}/patients']>>> {
    const response = await this.client.get(`/users/physiotherapists/${physiotherapistId}/patients`);
    return response.data;
  }

  /** Dissociate physiotherapist from patient */
  async dissociatePhysiotherapistPatient(associationId: string): Promise<ResponseType<PathItemMethod<paths['/users/assigned/{associationId}']>>> {
    const response = await this.client.delete(`/users/assigned/${associationId}`);
    return response.data;
  }

  /** Generate user invite */
  async generateUserInvite(inviteData: RequestBodyType<PathItemMethod<paths['/users/invite']>>): Promise<ResponseType<PathItemMethod<paths['/users/invite']>>> {
    const response = await this.client.post('/users/invite', inviteData);
    return response.data;
  }

  /** Create users in bulk */
  async createUsersBulk(usersData: RequestBodyType<PathItemMethod<paths['/users/invite/bulk']>>): Promise<ResponseType<PathItemMethod<paths['/users/invite/bulk']>>> {
    const response = await this.client.post('/users/invite/bulk', usersData);
    return response.data;
  }

  /** Resend user invite */
  async resendUserInvite(inviteId: string): Promise<ResponseType<PathItemMethod<paths['/users/invite/{inviteId}/resend']>>> {
    const response = await this.client.post(`/users/invite/${inviteId}/resend`);
    return response.data;
  }

  /** Delete user invite */
  async deleteUserInvite(id: string): Promise<ResponseType<PathItemMethod<paths['/users/invite/{id}']>>> {
    const response = await this.client.delete(`/users/invite/${id}`);
    return response.data;
  }

  /** Update user invite */
  async updateUserInvite(id: string, inviteData: RequestBodyType<PathItemMethod<paths['/users/invite/{id}']>>): Promise<ResponseType<PathItemMethod<paths['/users/invite/{id}']>>> {
    const response = await this.client.patch(`/users/invite/${id}`, inviteData);
    return response.data;
  }

  /** Update show popup setting */
  async updateShowPopup(id: string, popupData: RequestBodyType<PathItemMethod<paths['/users/associate-patient-physiotherapist/{id}/show-popup']>>): Promise<ResponseType<PathItemMethod<paths['/users/associate-patient-physiotherapist/{id}/show-popup']>>> {
    const response = await this.client.patch(`/users/associate-patient-physiotherapist/${id}/show-popup`, popupData);
    return response.data;
  }

  /** Get daily activity distribution */
  async getDailyActivityDistribution(userId: string): Promise<ResponseType<PathItemMethod<paths['/users/daily-activity-distribution/{userId}']>>> {
    const response = await this.client.get(`/users/daily-activity-distribution/${userId}`);
    return response.data;
  }

  /** Create daily activity distribution */
  async createDailyActivityDistribution(userId: string, activityData: RequestBodyType<PathItemMethod<paths['/users/daily-activity-distribution/{userId}']>>): Promise<ResponseType<PathItemMethod<paths['/users/daily-activity-distribution/{userId}']>>> {
    const response = await this.client.post(`/users/daily-activity-distribution/${userId}`, activityData);
    return response.data;
  }

  /** Update daily activity distribution */
  async updateDailyActivityDistribution(userId: string, activityData: RequestBodyType<PathItemMethod<paths['/users/daily-activity-distribution/{userId}']>>): Promise<ResponseType<PathItemMethod<paths['/users/daily-activity-distribution/{userId}']>>> {
    const response = await this.client.patch(`/users/daily-activity-distribution/${userId}`, activityData);
    return response.data;
  }

  /** Create or update user weight */
  async createOrUpdateWeight(userId: string, weightData: RequestBodyType<PathItemMethod<paths['/users/{userId}/weight']>>): Promise<ResponseType<PathItemMethod<paths['/users/{userId}/weight']>>> {
    const response = await this.client.post(`/users/${userId}/weight`, weightData);
    return response.data;
  }

  /** Create functional goals */
  async createFunctionalGoals(userId: string, goalsData: RequestBodyType<PathItemMethod<paths['/users/functional-goals/{userId}']>>): Promise<ResponseType<PathItemMethod<paths['/users/functional-goals/{userId}']>>> {
    const response = await this.client.post(`/users/functional-goals/${userId}`, goalsData);
    return response.data;
  }

  /** Update functional goals */
  async updateFunctionalGoals(userId: string, goalsData: RequestBodyType<PathItemMethod<paths['/users/functional-goals/{userId}']>>): Promise<ResponseType<PathItemMethod<paths['/users/functional-goals/{userId}']>>> {
    const response = await this.client.patch(`/users/functional-goals/${userId}`, goalsData);
    return response.data;
  }

  /** Update wellness check */
  async updateWellnessCheck(userId: string, wellnessData: RequestBodyType<PathItemMethod<paths['/users/{userId}/wellness-check']>>): Promise<ResponseType<PathItemMethod<paths['/users/{userId}/wellness-check']>>> {
    const response = await this.client.patch(`/users/${userId}/wellness-check`, wellnessData);
    return response.data;
  }

  /** Agree to pre-existing conditions */
  async agreeToPreExistingConditions(userId: string, agreementData: RequestBodyType<PathItemMethod<paths['/users/{userId}/pre-existing-conditions/agree']>>): Promise<ResponseType<PathItemMethod<paths['/users/{userId}/pre-existing-conditions/agree']>>> {
    const response = await this.client.patch(`/users/${userId}/pre-existing-conditions/agree`, agreementData);
    return response.data;
  }
}