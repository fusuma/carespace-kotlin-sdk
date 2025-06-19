/**
 * Base API class for all endpoints
 */
export class BaseAPI {
  constructor(client) {
    this.client = client;
  }

  buildUrl(path, params = {}) {
    let url = path;
    
    // Replace path parameters
    Object.keys(params).forEach(key => {
      url = url.replace(`{${key}}`, encodeURIComponent(params[key]));
    });
    
    return url;
  }

  buildQueryParams(params = {}) {
    const searchParams = new URLSearchParams();
    
    Object.keys(params).forEach(key => {
      const value = params[key];
      if (value !== undefined && value !== null && value !== '') {
        if (Array.isArray(value)) {
          value.forEach(item => searchParams.append(key, item));
        } else {
          searchParams.append(key, value);
        }
      }
    });
    
    const queryString = searchParams.toString();
    return queryString ? `?${queryString}` : '';
  }

  async get(path, params = {}, queryParams = {}) {
    const url = this.buildUrl(path, params) + this.buildQueryParams(queryParams);
    return this.client.get(url);
  }

  async post(path, data, params = {}) {
    const url = this.buildUrl(path, params);
    return this.client.post(url, data);
  }

  async put(path, data, params = {}) {
    const url = this.buildUrl(path, params);
    return this.client.put(url, data);
  }

  async patch(path, data, params = {}) {
    const url = this.buildUrl(path, params);
    return this.client.patch(url, data);
  }

  async delete(path, params = {}) {
    const url = this.buildUrl(path, params);
    return this.client.delete(url);
  }
}