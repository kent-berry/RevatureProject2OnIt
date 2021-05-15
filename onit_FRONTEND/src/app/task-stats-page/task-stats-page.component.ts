import { Component, OnInit } from '@angular/core';
import * as CanvasJS from './canvasjs.min';
import * as $ from 'jquery';
import { HttpStuffService } from '../http-stuff.service';



@Component({
  selector: 'app-task-stats-page',
  templateUrl: './task-stats-page.component.html',
  styleUrls: ['./task-stats-page.component.css']
})
export class TaskStatsPageComponent implements OnInit {

  pastNDaysOptionsList : number[] = [7,14,21,28];
  pastNDays : number = 7;


  constructor(private httpStuffService: HttpStuffService) { 

  }

  renderChart() : void {

    let dataPoints = [];
    let y = 0;		
    for ( var i = 0; i < this.pastNDays; i++ ) {		  
      y += Math.round(2 + Math.random() * (0 - 2));	
      dataPoints.push({ y: y});
    }
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
        dataPoints: dataPoints
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
  console.log("updatePastNDays called with arg = "+newPastNDays);
  var prevPastNDays  = this.pastNDays;
  this.pastNDays = newPastNDays;
  if (prevPastNDays != this.pastNDays) {
    this.renderChart();
  }
}
}

