import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserSignup } from '../common/user-signup';
import { Observable } from 'rxjs';
import { UserLogin } from '../common/user-login';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl: string = 'http://localhost:8080/todo-app/api/users';

  constructor(private httpClient: HttpClient) { }


  createNewUser(payload: UserSignup): Observable<any>{

    const searchUrl = `${this.baseUrl}/createUser`;

    return this.httpClient.post(searchUrl,payload);

  }

  loginUser(payload: UserLogin): Observable<any>{
    
    const searchUrl = `${this.baseUrl}/login`;

    return this.httpClient.post(searchUrl,payload);

  }

}
