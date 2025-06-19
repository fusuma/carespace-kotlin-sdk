import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * Reports API - Handles reports and analytics management
 */
export class ReportsAPI extends BaseAPI {
  /**
   * Reports and analytics management
   */

  /** Create report */
  async createReport(reportData: RequestBodyType<PathItemMethod<paths['/reports']>>): Promise<ResponseType<PathItemMethod<paths['/reports']>>> {
    const response = await this.client.post('/reports', reportData);
    return response.data;
  }

  /** Create report by feature and date */
  async createReportByFeatureAndDate(reportData: RequestBodyType<PathItemMethod<paths['/reports/create']>>): Promise<ResponseType<PathItemMethod<paths['/reports/create']>>> {
    const response = await this.client.post('/reports/create', reportData);
    return response.data;
  }

  /** Get all reports for user */
  async getAllReportsForUser(userId: string): Promise<ResponseType<PathItemMethod<paths['/reports/users/{userId}']>>> {
    const response = await this.client.get(`/reports/users/${userId}`);
    return response.data;
  }

  /** Get report by ID */
  async getReportById(reportId: string): Promise<ResponseType<PathItemMethod<paths['/reports/{reportId}']>>> {
    const response = await this.client.get(`/reports/${reportId}`);
    return response.data;
  }

  /** Update report */
  async updateReport(reportId: string, reportData: RequestBodyType<PathItemMethod<paths['/reports/{reportId}']>>): Promise<ResponseType<PathItemMethod<paths['/reports/{reportId}']>>> {
    const response = await this.client.patch(`/reports/${reportId}`, reportData);
    return response.data;
  }

  /** Delete report */
  async deleteReport(reportId: string): Promise<ResponseType<PathItemMethod<paths['/reports/{reportId}']>>> {
    const response = await this.client.delete(`/reports/${reportId}`);
    return response.data;
  }

  /** Update report notes */
  async updateReportNotes(reportId: string, notesData: RequestBodyType<PathItemMethod<paths['/reports/notes/{reportId}']>>): Promise<ResponseType<PathItemMethod<paths['/reports/notes/{reportId}']>>> {
    const response = await this.client.patch(`/reports/notes/${reportId}`, notesData);
    return response.data;
  }

  /** Get OmniROM CSV report */
  async getOmniRomCSVReport(): Promise<ResponseType<PathItemMethod<paths['/reports/omnirom/csv']>>> {
    const response = await this.client.get('/reports/omnirom/csv');
    return response.data;
  }

  /** Get LetsMove CSV report */
  async getLetsMoveCSVReport(): Promise<ResponseType<PathItemMethod<paths['/reports/letsmove/csv']>>> {
    const response = await this.client.get('/reports/letsmove/csv');
    return response.data;
  }

  /** Get aggregate Excel report */
  async getAggregateExcelReport(): Promise<ResponseType<PathItemMethod<paths['/reports/aggregate/excel']>>> {
    const response = await this.client.get('/reports/aggregate/excel');
    return response.data;
  }
}