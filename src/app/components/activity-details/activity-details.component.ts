import { Component, OnInit } from '@angular/core';
import { ActivityRest } from 'src/app/common/activity-rest';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityService } from 'src/app/services/activity.service';

@Component({
  selector: 'app-activity-details',
  templateUrl: './activity-details.component.html',
  styleUrls: ['./activity-details.component.css']
})
export class ActivityDetailsComponent implements OnInit {

  //Activity Response
  activityResponse: ActivityRest;

  //If the route has Activity Id
  hasActivityId: boolean;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private activityService: ActivityService) { }

  ngOnInit(): void {

    //Subscribing to the activated Route
    this.activatedRoute.paramMap.subscribe(
      () => this.showDetails()
    );

  }

  showDetails() {

    this.hasActivityId = this.activatedRoute.snapshot.paramMap.has('activityId');

    if(this.hasActivityId)
      this.showProductDetails();
    else
      this.router.navigateByUrl('');
  }

  showProductDetails() {

    const activityId = this.activatedRoute.snapshot.paramMap.get('activityId');

    //Subscribing to the service

    this.activityService.getActivityDetails(activityId).subscribe(
      data => {
        console.log('Activity Details Received -> '+JSON.stringify(data));
        this.activityResponse = data;
      }
    );

  }

  getCurrentDate(): Date{

    return new Date();

  }

}
