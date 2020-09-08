import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes,RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { EditorModule } from '@tinymce/tinymce-angular';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { TodoComponent } from './components/todo/todo.component';
import { CreateTodoComponent } from './components/create-todo/create-todo.component';
import { ActivityComponent } from './components/activity/activity.component'; 
import { CommonModule } from '@angular/common';
import { ActivityDetailsComponent } from './components/activity-details/activity-details.component';
import { CreateActivityComponent } from './components/create-activity/create-activity.component';
import { UserVerificationComponent } from './components/user-verification/user-verification.component';


// Declaring the routes

const routes: Routes = [
      
      {path: 'createActivity/:todoId', component: CreateActivityComponent},
      {path: 'activityDetails/:activityId', component: ActivityDetailsComponent},
      {path: 'showActivities/:todoId', component: ActivityComponent},
      {path: 'createTodo/:userId', component: CreateTodoComponent},
      {path: 'showTodos/:userId', component: TodoComponent},
      {path: 'userLogin', component: LoginComponent},
      {path: 'userVerification/:tokenId', component: UserVerificationComponent},
      {path: 'userSignup', component: SignupComponent}

];


//imports section

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    TodoComponent,
    CreateTodoComponent,
    ActivityComponent,
    ActivityDetailsComponent,
    CreateActivityComponent,
    UserVerificationComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    FontAwesomeModule,
    EditorModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
