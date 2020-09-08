import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivityRest } from '../common/activity-rest';
import { CreateActivity } from '../common/create-activity';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  baseUrl: string = 'http://localhost:8080/todo-app/api';

  constructor(private httpClient: HttpClient) { }


  getAllActivities(todoId: string): Observable<any> {

    const searchUrl = `${this.baseUrl}/activities/${todoId}/getActivities`;

    return this.httpClient.get<ActivityRest[]>(searchUrl);

  }

  getActivityDetails(activityId: string): Observable<any> {

    const searchUrl = `${this.baseUrl}/activities/${activityId}/getDetails`;

    return this.httpClient.get<ActivityRest>(searchUrl);

  }

  createNewActivity(payload: CreateActivity, todoId: string): Observable<any>{

    const searchUrl = `${this.baseUrl}/activities/${todoId}/createNewActivity`;

    return this.httpClient.post(searchUrl,payload);

  }
}
