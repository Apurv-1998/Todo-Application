import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CreateTodo } from 'src/app/common/create-todo';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TodoService } from 'src/app/services/todo.service';
import { TodoRest } from 'src/app/common/todo-rest';

@Component({
  selector: 'app-create-todo',
  templateUrl: './create-todo.component.html',
  styleUrls: ['./create-todo.component.css']
})
export class CreateTodoComponent implements OnInit {

  //Getting the user-id from the activated route
  userId: string;

  //form group
  createTodoForm: FormGroup;

  //Payload
  todoPayload: CreateTodo;

  //Todo Response
  todoRest: TodoRest;

  constructor(private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private toaster: ToastrService,
              private todoService: TodoService) {

                
    //Setting up the default payload value
    this.todoPayload = ({
      todoName: ''
    });
  
  }

  ngOnInit(): void {

    //Building the form
    this.createTodoForm = this.formBuilder.group({
      todoName: ['',Validators.required]
    });

  }


  onSubmit(){

    const hasUserId = this.activatedRoute.snapshot.paramMap.has('userId');

    if(hasUserId){

      const userId = this.activatedRoute.snapshot.paramMap.get('userId');

      this.userId = userId;

      //Handling the form
      console.log("TodoName -> "+this.createTodoForm.get('todoName').value);


      //Filling the payload
      this.todoPayload.todoName = this.createTodoForm.get('todoName').value;

      //Subscribing to the service

      this.todoService.createNewTodo(this.todoPayload,userId).subscribe(
        data => {
          console.log("Create Todo Data Received -> "+JSON.stringify(data));
          this.todoRest = data;
          this.router.navigate(['/showTodos/'+this.userId]);
          this.toaster.success("Todo Created");
        },
        error => {
          this.toaster.error("Cannot Create Todo");
          throw(error);
        }
      );


    }
    else{
      this.router.navigateByUrl('');
    }

  }

}
