import { Component, OnInit, NgModule } from '@angular/core';
import { ActivityRest } from 'src/app/common/activity-rest';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityService } from 'src/app/services/activity.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  //Activity Response
  activityResponse: ActivityRest[] = [];

  //Todo Id From the activated route
  todoId: string;

  //Check if the snapshot has todoId
  hasTodoId: boolean;

  //Getting the todoName from the route;
  todoName: string;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private activityService: ActivityService) { }

  ngOnInit(): void {

    //Subscribe to the activate route

    this.activatedRoute.paramMap.subscribe(
      () => {
        this.showActivities();
      }
    );

  }

  showActivities() {

    this.hasTodoId = this.activatedRoute.snapshot.paramMap.has('todoId');

    if(this.hasTodoId){
      this.listAllActivities();
    }
    else{
      this.router.navigateByUrl('');
    }

  }

  listAllActivities(){

    this.todoId = this.activatedRoute.snapshot.paramMap.get('todoId');

    //Subscribing to the service

    this.activityService.getAllActivities(this.todoId).subscribe(
      data => {
        console.log('Recieved Data -> '+JSON.stringify(data));
        this.activityResponse = data;
        this.todoName = this.activityResponse[0].todoName;
      }
    );

  }

  routeToCreateActivity(todoId: string) {

    this.router.navigate(['/createActivity/'+todoId]);

  }

}
