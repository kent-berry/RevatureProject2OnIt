import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { repeat } from 'rxjs/operators';
import { LocalDate } from '../DATES/LocalDate';
import { HttpStuffService } from '../http-stuff.service';
import { Task } from '../TASK_SERVICE/Task';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';


@Component({
  selector: 'app-create-task-page',
  templateUrl: './create-task-page.component.html',
  styleUrls: ['./create-task-page.component.css']
})
export class CreateTaskPageComponent implements OnInit {
  

  checked = false;
  indeterminate = false;
  labelPosition: 'before' | 'after' = 'after';
  disabled = false;

  repeatableChecked = false;
  repeatableIndeterminate = false;
  repeatableLabelPosition: 'before' | 'after' = 'after';
  repeatableDisabled = false;
  
  addLocation = false;
  lat: number;
  lng: number;
  marker = { lat: null, lng: null, alpha: 1 };
  zoomLevel: number;

  selectedMarker = null;
  minDate: Date;


  labelSelected : string;

  dueDateSelected;

  taskNameText: string;
  taskNotesText: string;

  missingInput : boolean = false;

  moveMarker(lat: number, lng: number): void {
    //console.log("     lat = "+this.marker.lat);
    //console.log("     lng = "+this.marker.lng);
    this.marker.lat = lat;
    this.marker.lng = lng;
  }

  dragMarker(lat: number, lng: number): void {
    this.marker.lat = lat;
    this.marker.lng = lng;
  }

  addingLocation(): any {
    const that = this;
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        that.lng = position.coords.longitude;
        that.lat = position.coords.latitude;
        that.marker.lat = that.lat;
        that.marker.lng = that.lng;
        that.zoomLevel = 10;
      }, () => {
        that.lat = 39.668;
        that.lng = -98.62;
        that.marker.lat = that.lat;
        that.marker.lng = that.lng;
        that.zoomLevel = 3.5;
      });
    }
  }

  selectMarker(event): void {
    this.marker.lat = event.latitude;
    this.marker.lng = event.longitude;
  }

  constructor(
    private _signedInUserService: SignedInUserService,
    private httpStuffService: HttpStuffService,
    private router: Router
  ) {
    const currentYear = new Date().getFullYear();
    const currentMonth = new Date().getMonth();
    const currentDay = new Date().getDate();
    this.minDate = new Date(currentYear, currentMonth, currentDay);
  }

  ngOnInit(): void {
  }



  createTaskClicked() {

    if (!this.taskNotesText || !this.labelSelected || !this.dueDateSelected) {
      // Print error message
      this.missingInput = true;
      return;
    }

    var taskID : number = -1;
    var userID : string = this.signedInUserService.user.id;
    var taskName : string = this.taskNameText;
    var Notes : string = this.taskNotesText;
    //var dueDate : LocalDate = new LocalDate(10, 10 ,1,1,1,1, 10, 1,1);
    var dueDate : LocalDate = null;
    if (this.dueDateSelected) {
      dueDate = new LocalDate(new Date(this.dueDateSelected).getFullYear(),   new Date(this.dueDateSelected).getMonth() + 1,   null,  null,   null,  
                      new Date(this.dueDateSelected).getMonth() + 1,  new Date(this.dueDateSelected).getDate(), new Date(this.dueDateSelected).getDay(), null);
    }
    var label : string = this.labelSelected;
    var dateCreated : LocalDate = new LocalDate(new Date().getFullYear(), new Date().getMonth() ,1,1,1,1, new Date().getDay(), 1,1);
    var dateCompleted : LocalDate = null;
    var reminder: number = 0;

    if (this.checked) {
      reminder = this.signedInUserService.user.receiveEmailReminders;
    }
    var repeatable : boolean = false;
    if (this.repeatableChecked) {
      repeatable = true;
    }
    var locationLat : number = null;
    var locationLng : number = null;

    if (this.addLocation) {
      locationLat = this.marker.lat;
      locationLng = this.marker.lng;
    }

      this.httpStuffService.createTaskRequest(taskName, Notes, dueDate, label, reminder, repeatable, locationLat, locationLng, this.signedInUserService.user).subscribe(
        responseTask => {
          //console.log("TASK CREATED, RECEIVED FULL COPY OF THE TASK");
          //console.log("TASK = "+responseTask);
          if (responseTask) {
            
            // Method 1: return the new task and add it to existing list on client side
             this.signedInUserService.addTask(responseTask);

          }
        }
      )

      this.router.navigate(['/tasks']);

  }

  get signedInUserService() {
    return this._signedInUserService;
  }

}
