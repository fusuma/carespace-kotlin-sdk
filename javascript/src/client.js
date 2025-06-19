/**
 * HTTP client for Carespace API
 */
export class CarespaceClient {
  constructor(config = {}) {
    this.baseURL = config.baseURL || 'https://api-dev.carespace.ai';
    this.apiKey = config.apiKey;
    this.timeout = config.timeout || 30000;
    this.defaultHeaders = {
      'Content-Type': 'application/json',
      ...config.headers
    };
  }

  setApiKey(apiKey) {
    this.apiKey = apiKey;
  }

  async request(method, url, options = {}) {
    const controller = new AbortController();
    const timeoutId = setTimeout(() => controller.abort(), this.timeout);

    const headers = {
      ...this.defaultHeaders,
      ...options.headers
    };

    if (this.apiKey) {
      headers.Authorization = `Bearer ${this.apiKey}`;
    }

    const config = {
      method: method.toUpperCase(),
      headers,
      signal: controller.signal,
      ...options
    };

    if (options.body && typeof options.body === 'object') {
      config.body = JSON.stringify(options.body);
    }

    try {
      const response = await fetch(`${this.baseURL}${url}`, config);
      clearTimeout(timeoutId);

      if (!response.ok) {
        if (response.status === 401) {
          throw new Error('Authentication failed. Please check your API key.');
        }
        
        let errorMessage = `HTTP ${response.status}: ${response.statusText}`;
        try {
          const errorData = await response.json();
          errorMessage = errorData.message || errorData.error || errorMessage;
        } catch (e) {
          // Ignore JSON parsing errors for error messages
        }
        
        throw new Error(errorMessage);
      }

      const contentType = response.headers.get('content-type');
      if (contentType && contentType.includes('application/json')) {
        return await response.json();
      }
      
      return response.text();
    } catch (error) {
      clearTimeout(timeoutId);
      if (error.name === 'AbortError') {
        throw new Error('Request timeout');
      }
      throw error;
    }
  }

  async get(url, options = {}) {
    return this.request('GET', url, options);
  }

  async post(url, data, options = {}) {
    return this.request('POST', url, { ...options, body: data });
  }

  async put(url, data, options = {}) {
    return this.request('PUT', url, { ...options, body: data });
  }

  async patch(url, data, options = {}) {
    return this.request('PATCH', url, { ...options, body: data });
  }

  async delete(url, options = {}) {
    return this.request('DELETE', url, options);
  }
}