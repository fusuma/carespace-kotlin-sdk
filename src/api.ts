import { CarespaceClient, CarespaceConfig } from './client';
import { paths, operations, components } from './types';
import { AuthAPI } from './api/auth';
import { UsersAPI } from './api/users';
import { ClientsAPI } from './api/clients';
import { ROMAPI } from './api/rom';
import { ProgramsAPI } from './api/programs';
import { ActivityStreamAPI } from './api/activity-stream';
import { SettingsAPI } from './api/settings';
import { EvaluationsAPI } from './api/evaluations';
import { ReportsAPI } from './api/reports';
import { SurveysAPI } from './api/surveys';
import { PostureAPI } from './api/posture';
import { StatsAPI } from './api/stats';
import { PlansAPI } from './api/plans';
import { VRAPI } from './api/vr';

// Helper types for extracting method types from paths
type PathItemMethod<T> = T extends { get: any } ? T['get'] : 
                        T extends { post: any } ? T['post'] :
                        T extends { put: any } ? T['put'] :
                        T extends { delete: any } ? T['delete'] :
                        T extends { patch: any } ? T['patch'] : never;

// Helper type for extracting response types (checking both 200 and 201 status codes)
type ResponseType<T> = T extends { responses: { 200: { content: { 'application/json': infer R } } } } ? R :
                      T extends { responses: { 201: { content: { 'application/json': infer R } } } } ? R : any;

// Helper type for extracting request body types
type RequestBodyType<T> = T extends { requestBody: { content: { 'application/json': infer R } } } ? R : never;

export class CarespaceAPI {
  private client: CarespaceClient;
  public auth: AuthAPI;
  public users: UsersAPI;
  public clients: ClientsAPI;
  public rom: ROMAPI;
  public programs: ProgramsAPI;
  public activityStream: ActivityStreamAPI;
  public settings: SettingsAPI;
  public evaluations: EvaluationsAPI;
  public reports: ReportsAPI;
  public surveys: SurveysAPI;
  public posture: PostureAPI;
  public stats: StatsAPI;
  public plans: PlansAPI;
  public vr: VRAPI;

  constructor(config: CarespaceConfig = {}) {
    this.client = new CarespaceClient(config);
    this.auth = new AuthAPI(this.client);
    this.users = new UsersAPI(this.client);
    this.clients = new ClientsAPI(this.client);
    this.rom = new ROMAPI(this.client);
    this.programs = new ProgramsAPI(this.client);
    this.activityStream = new ActivityStreamAPI(this.client);
    this.settings = new SettingsAPI(this.client);
    this.evaluations = new EvaluationsAPI(this.client);
    this.reports = new ReportsAPI(this.client);
    this.surveys = new SurveysAPI(this.client);
    this.posture = new PostureAPI(this.client);
    this.stats = new StatsAPI(this.client);
    this.plans = new PlansAPI(this.client);
    this.vr = new VRAPI(this.client);
  }

  setApiKey(apiKey: string): void {
    this.client.setApiKey(apiKey);
  }
}