import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { UserSignup } from 'src/app/common/user-signup';
import { UserRest } from 'src/app/common/user-rest';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  //Signup Form Group
  signupForm: FormGroup;

  //Create User Payload
  createUserPayload: UserSignup;

  //Create User Response
  userResponse: UserRest;

  constructor(private formBuilder: FormBuilder,
              private toaster: ToastrService,
              private router: Router,
              private userService: UserService) { 


    //Setting the default payload value
     this.createUserPayload = ({
       firstName: '',
       lastName: '',
       dob: '',
       email: '',
       password: ''
     });

  }

  ngOnInit(): void {

    //Creating the form

    this.signupForm = this.formBuilder.group({
      firstName: ['',Validators.required],
      lastName: ['',Validators.required],
      dob: ['',Validators.required],
      email: ['',Validators.required],
      password: ['',Validators.required]
    });

  }

  onSubmit(){

    //Handling form data
    console.log("Form Data");
    console.log("firstName -> "+this.signupForm.get('firstName').value);
    console.log("lastName -> "+this.signupForm.get('lastName').value);
    console.log("DOB -> "+this.signupForm.get('dob').value);
    console.log("Email -> "+this.signupForm.get('email').value);

    //Filling the payload
    this.createUserPayload.firstName = this.signupForm.get('firstName').value;
    this.createUserPayload.lastName = this.signupForm.get('lastName').value;
    this.createUserPayload.dob = this.signupForm.get('dob').value;
    this.createUserPayload.email = this.signupForm.get('email').value;
    this.createUserPayload.password = this.signupForm.get('password').value;

    //Calling the service and passing the payload

    this.userService.createNewUser(this.createUserPayload).subscribe(
      data => {
        console.log('Response Received -> '+JSON.stringify(data));
        this.userResponse = data;
        this.router.navigate(['/userLogin']);
        this.toaster.success("User Created");
      },
      error => {
        this.toaster.error("User Cannot Be Created");
        this.discard();
      }
    );

  }

  discard() {

    this.router.navigateByUrl('');

  }

}
