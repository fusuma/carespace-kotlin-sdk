import { BaseAPI, PathItemMethod, ResponseType, RequestBodyType } from './base';
import { paths } from '../types';

/**
 * VR API - Handles VR integration and authentication
 */
export class VRAPI extends BaseAPI {
  /**
   * VR integration and authentication
   */

  /** Generate VR code for user */
  async generateVRCode(userId: string): Promise<ResponseType<PathItemMethod<paths['/vr/generate-code/{userId}']>>> {
    const response = await this.client.post(`/vr/generate-code/${userId}`);
    return response.data;
  }

  /** Validate VR code */
  async validateVRCode(codeData: RequestBodyType<PathItemMethod<paths['/vr/validate-code']>>): Promise<ResponseType<PathItemMethod<paths['/vr/validate-code']>>> {
    const response = await this.client.post('/vr/validate-code', codeData);
    return response.data;
  }
}