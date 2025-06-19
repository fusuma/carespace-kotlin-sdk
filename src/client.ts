import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';

export interface CarespaceConfig {
  baseURL?: string;
  apiKey?: string;
  timeout?: number;
}

export class CarespaceClient {
  private http: AxiosInstance;
  private apiKey?: string;

  constructor(config: CarespaceConfig = {}) {
    this.apiKey = config.apiKey;
    
    this.http = axios.create({
      baseURL: config.baseURL || 'https://api-dev.carespace.ai',
      timeout: config.timeout || 30000,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    this.http.interceptors.request.use(
      (config) => {
        if (this.apiKey) {
          config.headers.Authorization = `Bearer ${this.apiKey}`;
        }
        return config;
      },
      (error) => Promise.reject(error)
    );

    this.http.interceptors.response.use(
      (response) => response,
      (error) => {
        if (error.response?.status === 401) {
          throw new Error('Authentication failed. Please check your API key.');
        }
        throw error;
      }
    );
  }

  setApiKey(apiKey: string): void {
    this.apiKey = apiKey;
  }

  async get<T = any>(url: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return this.http.get<T>(url, config);
  }

  async post<T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return this.http.post<T>(url, data, config);
  }

  async put<T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return this.http.put<T>(url, data, config);
  }

  async delete<T = any>(url: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return this.http.delete<T>(url, config);
  }

  async patch<T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return this.http.patch<T>(url, data, config);
  }
}