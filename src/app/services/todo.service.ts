import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TodoRest } from '../common/todo-rest';
import { CreateTodo } from '../common/create-todo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  baseUrl: string = "http://localhost:8080/todo-app/api";

  constructor(private httpClient: HttpClient) { }

  getAllTodos(userId: string): Observable<any>{

    const searchUrl = `${this.baseUrl}/todos/${userId}/showTodos`;

    return this.httpClient.get<TodoRest[]>(searchUrl);

  }

  createNewTodo(payload: CreateTodo, userId: string): Observable<any> {

    const searchUrl = `${this.baseUrl}/todos/${userId}/createTodo`;

    return this.httpClient.post(searchUrl,payload);

  }
}
