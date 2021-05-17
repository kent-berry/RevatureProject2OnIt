import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalDate } from '../DATES/LocalDate';
import { HttpStuffService } from '../http-stuff.service';
import { Task } from '../TASK_SERVICE/Task';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';



@Component({
  selector: 'app-edit-task-page',
  templateUrl: './edit-task-page.component.html',
  styleUrls: ['./edit-task-page.component.css']
})
export class EditTaskPageComponent implements OnInit {


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

  editingTask: Task;

  missingInput : boolean = false;


  constructor(
    private router: Router,
    private _signedInUserService: SignedInUserService,
    private httpStuffService: HttpStuffService
  ) {

      const currentYear = new Date().getFullYear();
      const currentMonth = new Date().getMonth();
      const currentDay = new Date().getDate();
      this.minDate = new Date(currentYear, currentMonth, currentDay);

    
      this.editingTask = this.signedInUserService.taskBeingEdited;
      if ( ! this.signedInUserService.taskBeingEdited ) {
        this.router.navigate(['/tasks'])
      }

    
  }

  moveMarker(lat: number, lng: number): void {
    //console.log('moveMarker: new lat is ' + lat);
    //console.log('moveMarker: new lng is ' + lng);
    this.marker.lat = lat;
    this.marker.lng = lng;
  }

  dragMarker(lat: number, lng: number): void {
    //console.log('dragMarker: new lat is  ' + lat);
    //console.log('dragMarker: new lng is ' + lng);
    this.marker.lat = lat;
    this.marker.lng = lng;
  }

  selectMarker(event): void {
    this.marker.lat = event.latitude;
    this.marker.lng = event.longitude;
  }

  addingLocation(): void {
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
  

  addingLocationInitValue(): void {
    const that = this;
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        that.lng = that.editingTask.longitude;
        that.lat = that.editingTask.latitude;
        that.marker.lat = that.lat;
        that.marker.lng = that.lng;
        that.zoomLevel = 10;
      }, () => {
        that.lng = that.editingTask.longitude;
        that.lat = that.editingTask.latitude;
        that.marker.lat = that.lat;
        that.marker.lng = that.lng;
        that.zoomLevel = 10;
      });
    }
  }


  ngOnInit(): void {

    
    
    
    this.taskNameText = this.editingTask.taskName;
    this.taskNotesText = this.editingTask.notes;

    if (this.editingTask.reminder) {
      this.checked = true;
    }

    this.dueDateSelected = new Date(this.editingTask.dueDate.month+"/"+this.editingTask.dueDate.dayOfMonth+"/"+this.editingTask.dueDate.year);

    this.repeatableChecked = false;
    if (this.editingTask.repeatable) {
      this.repeatableChecked = true;
    }
    
    
      
      //console.log("INITIALIZING EDIT TASK LAT: "+this.editingTask.latitude);
      //console.log("INITIALIZING EDIT TASK LNG: "+this.editingTask.longitude);
      
      

      if (this.editingTask.latitude) {
        this.addingLocationInitValue();
        this.addLocation = true;
      }
      else {
        this.addingLocation();
        this.addLocation = false;
      }

      if (this.editingTask == null) {
        this.labelSelected = "General";
      } else {
      this.labelSelected = this.editingTask.taskLabel_fk;
      }

    

  }


  updateTaskClicked() {

      if (!this.editingTask || !this.taskNotesText || !this.labelSelected || !(new Date(this.dueDateSelected).getDate())) {
        // Print error message
        this.missingInput = true;
        return;
      }

      this.editingTask.taskName = this.taskNameText;
      this.editingTask.notes = this.taskNotesText;

      var dueDate : LocalDate = null;
      this.editingTask.dueDate = dueDate;
      if (this.dueDateSelected) {
        dueDate = new LocalDate(new Date(this.dueDateSelected).getFullYear(),   new Date(this.dueDateSelected).getMonth() + 1,   null,  null,   null,  
                        new Date(this.dueDateSelected).getMonth() + 1,  new Date(this.dueDateSelected).getDate(), new Date(this.dueDateSelected).getDay(), null);
                        this.editingTask.dueDate = dueDate;
      }
      var label : string = this.labelSelected;
      this.editingTask.taskLabel_fk = this.labelSelected;

  
      this.editingTask.reminder = 0;
      if (this.checked) {
        this.editingTask.reminder = this.signedInUserService.user.receiveEmailReminders;
      }
      var repeatable : boolean = false;
      if (this.repeatableChecked) {
        repeatable = true;
      }

      this.editingTask.latitude = null;
      this.editingTask.longitude = null;
  
      if (this.addLocation) {
        this.editingTask.latitude = this.marker.lat;
        this.editingTask.longitude = this.marker.lng;
      }
  
      
        this.httpStuffService.updateTaskRequest(this.editingTask, this.signedInUserService.user).subscribe(
          responseTask => {
            //console.log("TASK ***UPDATED***, RECEIVED FULL COPY OF THE TASK");
            //console.log("*** TASK = "+responseTask);
            if (responseTask) {
              
              // Method 1: adding the returned task to list of tasks
              //this.signedInUserService.updateTasks(responseTask.id, responseTask);
              // Method 2: refetching all tasks for the server
              this.signedInUserService.tasks = responseTask;
              

            }
          }
        )
  
        this.router.navigate(['/tasks']);
  
    

  }


  deleteTaskClicked() {

    
      this.httpStuffService.deleteTaskRequest(this.editingTask, this.signedInUserService.user).subscribe(
        responseTasks => {
          
          
          //console.log("*** TASK DELETE --> response = "+responseTasks);

            this.signedInUserService.tasks = responseTasks;
           
        }
      )

      this.router.navigate(['/tasks']);

  

  }


  get signedInUserService() {
    return this._signedInUserService;
  }
  
}
