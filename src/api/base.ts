import { CarespaceClient } from '../client';
import { paths, operations, components } from '../types';

// Helper types for extracting method types from paths
export type PathItemMethod<T> = T extends { get: any } ? T['get'] : 
                                T extends { post: any } ? T['post'] :
                                T extends { put: any } ? T['put'] :
                                T extends { delete: any } ? T['delete'] :
                                T extends { patch: any } ? T['patch'] : never;

// Helper type for extracting response types (checking both 200 and 201 status codes)
export type ResponseType<T> = T extends { responses: { 200: { content: { 'application/json': infer R } } } } ? R :
                              T extends { responses: { 201: { content: { 'application/json': infer R } } } } ? R : any;

// Helper type for extracting request body types
export type RequestBodyType<T> = T extends { requestBody: { content: { 'application/json': infer R } } } ? R : never;

// Base class for all API categories
export abstract class BaseAPI {
  protected client: CarespaceClient;

  constructor(client: CarespaceClient) {
    this.client = client;
  }
}