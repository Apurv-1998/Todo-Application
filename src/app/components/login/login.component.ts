import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserLogin } from 'src/app/common/user-login';
import { UserRest } from 'src/app/common/user-rest';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //Login Form
  userLoginForm: FormGroup;

  //Login Payload
  userLoginPayload: UserLogin;

  //Login Response
  userRest: UserRest;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private toaster: ToastrService,
              private userService: UserService) {


    //Initializing the userPayload

    this.userLoginPayload = ({
      userName: '',
      password: ''
    });
  
  }

  ngOnInit(): void {

    //Initializing the form builder
    this.userLoginForm = this.formBuilder.group({
      userName: ['',Validators.required],
      password: ['',[Validators.required,Validators.maxLength(16),Validators.minLength(3)]]
    });


  }

  onSubmit() {

    //Handling form
    console.log("Handling Form");
    console.log("User Name -> "+this.userLoginForm.get('userName').value);
    console.log("Password -> "+this.userLoginForm.get('password').value);

    //Filling the payload
    this.userLoginPayload.userName = this.userLoginForm.get('userName').value;
    this.userLoginPayload.password = this.userLoginForm.get('password').value;

    //Calling the service
    this.userService.loginUser(this.userLoginPayload).subscribe(
      data => {
        console.log('Login Data Received -> '+JSON.stringify(data));
        this.userRest = data;

        //Add The Navigation URL

        this.router.navigate(['/showTodos/'+this.userRest.userId]);

        this.toaster.success("Login Successful");
      },
      error => {
        this.toaster.error("Invalid Credentials");
        this.router.navigate(['/userSignup']);
      }
    );

  }

  discard() {

    this.router.navigateByUrl('');

  }

}
