import { Component, OnInit } from '@angular/core';
import * as CanvasJS from './canvasjs.min';
import * as $ from 'jquery';
import { HttpStuffService } from '../http-stuff.service';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';
import { Task } from '../TASK_SERVICE/Task';



@Component({
  selector: 'app-task-stats-page',
  templateUrl: './task-stats-page.component.html',
  styleUrls: ['./task-stats-page.component.css']
})
export class TaskStatsPageComponent implements OnInit {

  pastNDaysOptionsList : number[] = [7,14,21,28];
  
  nSelected = 7;

  constructor(private _httpStuffService: HttpStuffService,
    private _signedInUserService: SignedInUserService) { 

  }

  renderChart() : void {


    /*
      Generate task list for rendering
    */



    

    var today = new Date();

    var tasks: Task[] = this.signedInUserService.tasks;


    // Initialize an array of size nSelected with each element set to 0 (for num tasks complete on each day)
    var tasksPerDay : number[] = new Array<number>(this.nSelected).fill(0);
    if (tasks) {
    tasks.forEach(task =>
      {
        if (task.dateCompleted) {
          var timeAgo = (new Date()).getTime() - new Date(task.dateCompleted.month+"/"+task.dateCompleted.dayOfMonth+"/"+task.dateCompleted.year).getTime();
          var daysAgo = Math.floor(timeAgo / (1000 * 3600 * 24));

          if (daysAgo < this.nSelected) {
            tasksPerDay[daysAgo] = tasksPerDay[daysAgo] + 1;
          }
          
        }
      });
    }

    




    let daysAgoList = [];
    for (var k = 0; k < this.nSelected; k++) {
      daysAgoList.push({y : tasksPerDay[k]});
    }
    let y = tasksPerDay;		
    
    let chart = new CanvasJS.Chart("chartContainer", {
      backgroundColor: "transparent",
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: ""
      },
      subtitles:[{
        text: ""
      }],
      data: [
      {
        type: "spline",                
        dataPoints: daysAgoList
      }],
      axisY: {
        gridThickness: 0,
        stripLines: [
          {
            value: 0,
            showOnTop: true,
            color: "gray",
            thickness: 2
          }
        ],
        title: "Tasks complete"
     },

     axisX: {
       title: "Days ago"
     }
    });
      
    chart.render();

  }
  ngOnInit(): void {

 /*
    this.httpStuffService.checkSessionAsync().subscribe( resp => {
      console.log("CHECK_SESSION_RESPONSE = "+resp);
            if (resp == null) {
              console.log("session does not exist");
            }
            else {
              console.log("session exists");
              console.log("resp = "+resp);
            }
          });
*/
    this.renderChart();

}
updatePastNDays(newPastNDays : number) {
 /* console.log("updatePastNDays called with arg = "+newPastNDays);
  var prevPastNDays  = this.pastNDays;
  this.pastNDays = newPastNDays;
  if (prevPastNDays != this.pastNDays) {
    this.renderChart();
  }
*/
  this.renderChart();
}



downloadTaskData() {

  this.httpStuffService.requestDownloadData(this.signedInUserService.user).subscribe(
    data => {

      //printObj(data);

      
      const blob = new Blob([data.formString], { type: 'text/csv'});
      const url= window.URL.createObjectURL(blob);
      window.open(url);
    

    }
  )
}


get signedInUserService() {
  return this._signedInUserService;
}

get httpStuffService() {
  return this._httpStuffService;
}




}



function printObj(obj) {
  Object.keys(obj).forEach((prop)=> console.log(prop+":  "+obj[prop]));
}
