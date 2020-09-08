import { Component, OnInit } from '@angular/core';
import { TodoRest } from 'src/app/common/todo-rest';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoService } from 'src/app/services/todo.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  //Response to show the todos
  todoResponse: TodoRest[] = [];

  //storing the userId
  userId: string;

  //checking whether the route has the userId
  hasUserId: boolean;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private todoService: TodoService ) { }

  ngOnInit(): void {

    //subscribing to the activated route
    this.activatedRoute.paramMap.subscribe(
      () => this.listTodos()
    );


  }

  listTodos() {

    this.hasUserId = this.activatedRoute.snapshot.paramMap.has('userId');

    if(this.hasUserId)
      this.listAllTodos();
    else
      this.router.navigateByUrl('');

  }

  listAllTodos(){

    const userId = this.activatedRoute.snapshot.paramMap.get('userId');

    this.userId = userId;

    //Subscribing to the service

    this.todoService.getAllTodos(userId).subscribe(
      data => {
        console.log('Data Received -> '+JSON.stringify(data));
        this.todoResponse = data;
      }
    );

  }


  // Add the router link to the create-todo-component
  routeToCreateTodo(userId: string){

    console.log("Entered");

    this.router.navigate(['/createTodo/'+userId]);

  }

}
