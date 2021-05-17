import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Task } from '../TASK_SERVICE/Task';
import { Router, ActivatedRoute, NavigationEnd, NavigationStart } from '@angular/router';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';
import { Subscription } from 'rxjs';
import { HttpStuffService } from '../http-stuff.service';
import { LocalDate } from '../DATES/LocalDate';


interface Label {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})
export class UserHomePageComponent implements OnInit {

  @Input()

  tasks : Task[] = [];

  tasksFiltered : Task[] = [];
  page = 0;
  size = 12;

  taskSelected : boolean = false;
  taskBeingViewed : Task;


  subscription : Subscription;

  labels: Label[] = [
    {value: 'All', viewValue: 'All'},
    {value: 'General', viewValue: 'General'},
    {value: 'Sports', viewValue: 'Sports'},
    {value: 'Chores', viewValue: 'Chores'},
    {value: 'Cooking', viewValue: 'Cooking'},
    {value: 'Entertainment', viewValue: 'Entertainment'},
    {value: 'Dining', viewValue: 'Dining'},
    {value: 'Education', viewValue: 'Education'},
    {value: 'Travel', viewValue: 'Travel'},
    {value: 'Work', viewValue: 'Work'}
    ];
  

  // Form input stuff for this component's template
  filterByLabel;
  pendingChecked;
  completedChecked;
  repeatableChecked;
  
  showCompleted: boolean = true;;
  showPending: boolean = true;
  showRepeatable: boolean = true;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private _signedInUserService: SignedInUserService,
    private httpStuffService: HttpStuffService
  ) {


    
    
   }

   get signedInUserService() {
    return this._signedInUserService;
  }

  ngOnInit(): void {
    //console.log("\n\n\nUSER HOME PAGE: NG ON INIT")

    this.filterByLabel = "All";
    this.completedChecked = true;
    this.pendingChecked = true;
    this.repeatableChecked = true;
    // Subscribe to SignedInUserService tasksUpdated Event Emitter
    this.signedInUserService.tasksUpdated$.subscribe(
      action => {
        
        /*
        *
        * This is the function called on TASKS PAGE whenever the User Task List is updated
        *   
        */
          this.tasks = this.signedInUserService.tasks;
          this.applyTaskFilters();

      }
    );

    
  
  }

  getData(obj) {
    let index=0,
        startingIndex=obj.pageIndex * obj.pageSize,
        endingIndex=startingIndex + obj.pageSize;

      if (this.signedInUserService.tasks == null) {
        this.tasksFiltered = [];
      }
      else {
        this.tasksFiltered = this.signedInUserService.tasks.filter(() => {
          index++;
          return (index > startingIndex && index <= endingIndex) ? true : false;
        });
    }
  }



  selectTask(task : Task) {

    this.signedInUserService.taskBeingEdited = task;
    this.router.navigate(['/editviewtask'])




  }


  ngOnChanges(changes: SimpleChanges) {
    //console.log("changes detected");
  }

  completeTask(task: Task) {

    var now : Date = new Date();

    var newDue = new Date();
    newDue.setDate(newDue.getDate() + 7);
    console.log("NEW DUE IS "+newDue);

    var newDueDate: LocalDate;
    if (task.repeatable) {
      console.log("NEW DUE MONTH IS "+newDue.getMonth())
      newDueDate = new LocalDate(newDue.getFullYear(), newDue.getMonth() + 1, null, null, null, newDue.getMonth() + 1, newDue.getDate(),null,null);
    }
    else {
      newDueDate = task.dueDate;
    }
    
    var newDateCompleted: LocalDate;
    if (task.repeatable) {
      newDateCompleted = null;
    }
    else {
      newDateCompleted = new LocalDate(now.getFullYear(), now.getMonth() + 1, null, null, null, now.getMonth() + 1, now.getDate(), null, null)
    }


    var updatedTask : Task = new Task(task.id, this.signedInUserService.user.id, task.taskName, task.notes, newDueDate, task.taskLabel_fk,
      task.dateCreated, newDateCompleted, task.reminder,
      task.repeatable, task.latitude, task.longitude);

    this.httpStuffService.updateTaskRequest(updatedTask, this.signedInUserService.user).subscribe(
      responseTask => {
        //console.log("TASK ***UPDATED***, RECEIVED FULL COPY OF THE TASK");
        //console.log("*** TASK = "+responseTask);
        if (responseTask) {
          this.signedInUserService.tasks =  responseTask;
        }
      }
    )

  }





  labelSelected() {

      this.applyTaskFilters();

  }

  applyTaskFilters() {

    this.tasksFiltered = [];

      if (this.tasks) {
      this.tasks.sort((a,b) => 
      {
        if (a.dueDate.dayOfYear > b.dueDate.dayOfYear) {
          return 1;
        } else if (a.dueDate.dayOfYear < b.dueDate.dayOfYear) {
          return -1;
        } else {
          return 0;
        }
      });
    
      // Handle Label/Category filter
    this.signedInUserService.tasks.forEach(task => {
      //console.log(" inspecting task: date completed is "+task.dateCompleted +" with this.pendingChecked == "+this.pendingChecked);
      if (this.filterByLabel == 'All'    ||    task.taskLabel_fk == this.filterByLabel) {
        if ( (task.dateCompleted && this.showCompleted) || (!task.dateCompleted && this.showPending) ) {

            
              this.tasksFiltered.push(task);
            
        }
      }

    });

    

    this.getData_STARTING_WITH_TASKS_FILTERED({pageIndex: this.page, pageSize: this.size});

  }

  }

  getData_STARTING_WITH_TASKS_FILTERED(obj) {
    let index=0,
        startingIndex=obj.pageIndex * obj.pageSize,
        endingIndex=startingIndex + obj.pageSize;

      if (this.tasksFiltered == null) {
        this.tasksFiltered = [];
      }
      else {
        this.tasksFiltered = this.tasksFiltered.filter(() => {
          index++;
          return (index > startingIndex && index <= endingIndex) ? true : false;
        });
    }
  }


  checkboxClicked(name: string) {
    if (name == "Completed") {
      this.showCompleted = ! this.showCompleted;
    }
    else if (name == "Pending") {
      this.showPending = ! this.showPending;
    }
    else if (name == "Repeatable") {
      this.showRepeatable = ! this.showRepeatable;
    }
    
    //console.log("checkboxclicked --> name = "+name+" and event is = "+event);
    this.applyTaskFilters();
  }

  
}
