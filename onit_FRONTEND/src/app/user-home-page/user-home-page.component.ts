import { Component, OnInit } from '@angular/core';
import { Task } from '../TASK_SERVICE/Task';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})
export class UserHomePageComponent implements OnInit {

  tasks : Task[] = [ new Task(10, 4, "Do the dishes", "Dishes are wet", new Date(2018, 0O5, 0O5, 17, 23, 42, 11), "Chores",
                           new Date(2018, 0O5, 0O5, 17, 23, 42, 11), new Date(2018, 0O5, 0O5, 17, 23, 42, 11), 2, true),

                    new Task(10, 4, "Do the dishes more often!", "Dishes are wet", new Date(2018, 0O5, 0O5, 17, 23, 42, 11), "Chores",
                    new Date(2018, 0O5, 0O5, 17, 23, 42, 11), new Date(2018, 0O5, 0O5, 17, 23, 42, 11), 2, true),

                    new Task(10, 4, "Do the dishes again!!", "Dishes are wet", new Date(2018, 0O5, 0O5, 17, 23, 42, 11), "Chores",
                    new Date(2018, 0O5, 0O5, 17, 23, 42, 11), new Date(2018, 0O5, 0O5, 17, 23, 42, 11), 2, true)

                    ];
  tasksFiltered : any[] = [];
  page = 0;
  size = 12;

  taskSelected : boolean = false;
  taskBeingViewed : Task;
  

  labels: string[] = ["Cooking", "School", "Work", "Exercise"];


  constructor(
    private router: Router,
    private route: ActivatedRoute,
  ) {

   }

  ngOnInit(): void {
    this.getData({pageIndex: this.page, pageSize: this.size});

  }


  
  getData(obj) {
    let index=0,
        startingIndex=obj.pageIndex * obj.pageSize,
        endingIndex=startingIndex + obj.pageSize;

    this.tasksFiltered = this.tasks.filter(() => {
      index++;
      return (index > startingIndex && index <= endingIndex) ? true : false;
    });
  }


  selectTask(task : any) {

    this.taskSelected = true;
    this.taskBeingViewed = task;
    this.router.navigate(['/editviewtask/2'])




  }

}
