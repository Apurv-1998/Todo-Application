import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CreateActivity } from 'src/app/common/create-activity';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityService } from 'src/app/services/activity.service';
import { ToastrService } from 'ngx-toastr';
import { ActivityRest } from 'src/app/common/activity-rest';

@Component({
  selector: 'app-create-activity',
  templateUrl: './create-activity.component.html',
  styleUrls: ['./create-activity.component.css']
})
export class CreateActivityComponent implements OnInit {

  //Create Activity Form
  activityForm: FormGroup;

  //Has The todoId
  hasTodoId: boolean;

  //Store the todoId from the route
  todoId: string;

  //Send The Payload
  activityPayload: CreateActivity;

  //Response of creation
  activityResponse: ActivityRest;

  constructor(private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private activityService: ActivityService,
              private toaster: ToastrService) { 

    //Initilizing the payload

    this.activityPayload = ({
      activityName: '',
      text: '',
      startDate: new Date(),
      endDate: new Date()
    });

  }

  ngOnInit(): void {

    //building the form
    this.activityForm = this.formBuilder.group({
      activityName: ['',Validators.required],
      text: ['',Validators.required],
      startDate: ['',Validators.required],
      endDate: ['',Validators.required]
    });

  }

  onSubmit(){

    this.hasTodoId = this.activatedRoute.snapshot.paramMap.has('todoId');

    if(this.hasTodoId){

      this.todoId = this.activatedRoute.snapshot.paramMap.get('todoId');


      //Filling the payload
      this.activityPayload.activityName = this.activityForm.get('activityName').value;
      this.activityPayload.text = this.activityForm.get('text').value;
      this.activityPayload.startDate = this.activityForm.get('startDate').value;
      this.activityPayload.endDate = this.activityForm.get('endDate').value;

      //Calling the service

      this.activityService.createNewActivity(this.activityPayload,this.todoId).subscribe(
        data => {
          console.log('Data Received -> '+JSON.stringify(data));
          this.activityResponse = data;
          this.toaster.success("Activity Created");
          this.router.navigate(['/showActivities/'+this.todoId]);
        },
        error => {
          this.toaster.error("Cannot Create Activity");
          throw(error);
        }
      );

    }
    else{
      this.router.navigateByUrl('');
    }


  }

}
