import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Task } from '../TASK_SERVICE/Task';
import { Router, ActivatedRoute, NavigationEnd, NavigationStart } from '@angular/router';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';
import { Subscription } from 'rxjs';
import { HttpStuffService } from '../http-stuff.service';




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
  

  labels: string[] = ["Cooking", "School", "Work", "Exercise"];

  subscription : Subscription;
  

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

    // Now fetch user tasks
    this.httpStuffService.requestUserTasks(this.signedInUserService.user).subscribe(
      responseTasks => {
        console.log("Get Task list response received!");
        this.signedInUserService.tasks = responseTasks;
        console.log("RESPONSE TASKS: "+responseTasks);
        console.log("(TASK 0).taskName is : "+responseTasks[0].dueDate.year);
      }
    )  

    // Subscribe to SignedInUserService tasksUpdated Event Emitter
    this.signedInUserService.tasksUpdated$.subscribe(
      action => {
        if (action == "tasksUpdated") {
          console.log("USER HOME: TASK UPDATE DETECTED");
          this.tasks = this.signedInUserService.tasks;
          this.getData({pageIndex: this.page, pageSize: this.size});

        }
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


  selectTask(task : any) {

    this.taskSelected = true;
    this.taskBeingViewed = task;
    this.router.navigate(['/editviewtask/2'])




  }


  ngOnChanges(changes: SimpleChanges) {
    console.log("changes detected");
  }

}
