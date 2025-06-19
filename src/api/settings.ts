import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * Settings API - Handles settings and configuration management
 */
export class SettingsAPI extends BaseAPI {
  /**
   * Settings and configuration management
   */

  /** Get all settings */
  async getAllSettings(): Promise<ResponseType<PathItemMethod<paths['/settings']>>> {
    const response = await this.client.get('/settings');
    return response.data;
  }

  /** Create settings */
  async createSettings(settingsData: RequestBodyType<PathItemMethod<paths['/settings']>>): Promise<ResponseType<PathItemMethod<paths['/settings']>>> {
    const response = await this.client.post('/settings', settingsData);
    return response.data;
  }

  /** Update settings */
  async updateSettings(settingsId: string, settingsData: RequestBodyType<PathItemMethod<paths['/settings/{settingsId}']>>): Promise<ResponseType<PathItemMethod<paths['/settings/{settingsId}']>>> {
    const response = await this.client.patch(`/settings/${settingsId}`, settingsData);
    return response.data;
  }

  /** Get email template */
  async getEmailTemplate(): Promise<ResponseType<PathItemMethod<paths['/settings/templates/email']>>> {
    const response = await this.client.get('/settings/templates/email');
    return response.data;
  }

  /** Create or update email template */
  async createOrUpdateEmailTemplate(templateData: RequestBodyType<PathItemMethod<paths['/settings/templates/email']>>): Promise<ResponseType<PathItemMethod<paths['/settings/templates/email']>>> {
    const response = await this.client.post('/settings/templates/email', templateData);
    return response.data;
  }

  /** Get invite template */
  async getInviteTemplate(): Promise<ResponseType<PathItemMethod<paths['/settings/templates/invite']>>> {
    const response = await this.client.get('/settings/templates/invite');
    return response.data;
  }

  /** Create or update invite template */
  async createOrUpdateInviteTemplate(templateData: RequestBodyType<PathItemMethod<paths['/settings/templates/invite']>>): Promise<ResponseType<PathItemMethod<paths['/settings/templates/invite']>>> {
    const response = await this.client.post('/settings/templates/invite', templateData);
    return response.data;
  }

  /** Get ROM email template */
  async getRomEmailTemplate(): Promise<ResponseType<PathItemMethod<paths['/settings/templates/email/rom']>>> {
    const response = await this.client.get('/settings/templates/email/rom');
    return response.data;
  }

  /** Create or update ROM email template */
  async createOrUpdateRomEmailTemplate(templateData: RequestBodyType<PathItemMethod<paths['/settings/templates/email/rom']>>): Promise<ResponseType<PathItemMethod<paths['/settings/templates/email/rom']>>> {
    const response = await this.client.post('/settings/templates/email/rom', templateData);
    return response.data;
  }

  /** Get theme settings */
  async getThemeSettings(): Promise<ResponseType<PathItemMethod<paths['/settings/themes']>>> {
    const response = await this.client.get('/settings/themes');
    return response.data;
  }

  /** Create or update theme settings */
  async createOrUpdateThemeSettings(themeData: RequestBodyType<PathItemMethod<paths['/settings/themes']>>): Promise<ResponseType<PathItemMethod<paths['/settings/themes']>>> {
    const response = await this.client.post('/settings/themes', themeData);
    return response.data;
  }

  /** Get network settings */
  async getNetworkSettings(): Promise<ResponseType<PathItemMethod<paths['/settings/network']>>> {
    const response = await this.client.get('/settings/network');
    return response.data;
  }

  /** Create or update network settings */
  async createOrUpdateNetworkSettings(networkData: RequestBodyType<PathItemMethod<paths['/settings/network']>>): Promise<ResponseType<PathItemMethod<paths['/settings/network']>>> {
    const response = await this.client.post('/settings/network', networkData);
    return response.data;
  }

  /** Get premium plans status settings */
  async getPremiumPlansStatusSettings(): Promise<ResponseType<PathItemMethod<paths['/settings/premium-plans/status']>>> {
    const response = await this.client.get('/settings/premium-plans/status');
    return response.data;
  }

  /** Create or update premium plans status settings */
  async createOrUpdatePremiumPlansStatusSettings(plansData: RequestBodyType<PathItemMethod<paths['/settings/premium-plans/status']>>): Promise<ResponseType<PathItemMethod<paths['/settings/premium-plans/status']>>> {
    const response = await this.client.post('/settings/premium-plans/status', plansData);
    return response.data;
  }

  /** Get plan settings */
  async getPlanSettings(): Promise<ResponseType<PathItemMethod<paths['/settings/plans']>>> {
    const response = await this.client.get('/settings/plans');
    return response.data;
  }

  /** Create or update plan settings */
  async createOrUpdatePlanSettings(planData: RequestBodyType<PathItemMethod<paths['/settings/plans']>>): Promise<ResponseType<PathItemMethod<paths['/settings/plans']>>> {
    const response = await this.client.post('/settings/plans', planData);
    return response.data;
  }

  /** Get functional goals settings */
  async getFunctionalGoalsSettings(): Promise<ResponseType<PathItemMethod<paths['/settings/functional-goals']>>> {
    const response = await this.client.get('/settings/functional-goals');
    return response.data;
  }

  /** Create or update functional goals settings */
  async createOrUpdateFunctionalGoalsSettings(goalsData: RequestBodyType<PathItemMethod<paths['/settings/functional-goals']>>): Promise<ResponseType<PathItemMethod<paths['/settings/functional-goals']>>> {
    const response = await this.client.post('/settings/functional-goals', goalsData);
    return response.data;
  }

  /** Get pre-existing conditions settings */
  async getPreExistingConditionsSettings(): Promise<ResponseType<PathItemMethod<paths['/settings/pre-existing-conditions']>>> {
    const response = await this.client.get('/settings/pre-existing-conditions');
    return response.data;
  }

  /** Create or update pre-existing conditions settings */
  async createOrUpdatePreExistingConditionsSettings(conditionsData: RequestBodyType<PathItemMethod<paths['/settings/pre-existing-conditions']>>): Promise<ResponseType<PathItemMethod<paths['/settings/pre-existing-conditions']>>> {
    const response = await this.client.post('/settings/pre-existing-conditions', conditionsData);
    return response.data;
  }
}