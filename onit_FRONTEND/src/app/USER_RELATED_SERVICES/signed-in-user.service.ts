import { Injectable } from '@angular/core';
import * as EventEmitter from 'events';
import { BehaviorSubject } from 'rxjs';
import { Task } from '../TASK_SERVICE/Task';
import { User } from './User';


interface Label {
  value: string;
  viewValue: string;
}

@Injectable({
  providedIn: 'root'
})
export class SignedInUserService {

  private _user : User;
  private _tasks : Task[];

  tasksUpdatedSource = new BehaviorSubject<string>("tasksUpdated");
  tasksUpdated$ = this.tasksUpdatedSource.asObservable();

  tasksWereFetched: boolean = false;
  

  taskBeingEdited: Task;



  labels: Label[] = [
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

  constructor() {

   }


   get user() {
     return this._user;
   }
   set user(user: User) {
     this._user = user;
   }

   get tasks() {
    return this._tasks;
    
  }
  set tasks(tasks: Task[]) {
    
    this._tasks= tasks;
    this.tasksUpdatedSource.next("tasksUpdated");
    
  }

  addTask(task: Task) {
    
    if (!this.tasks) {
      this.tasks = [];
    }
    this.tasks.push(task);
    this.tasksUpdatedSource.next("tasksUpdated");
    

  }

  removeTask(task: Task) {
    
  }

  updateTasks(taskId: string, updatedTask: Task) {

    var index = -1;
    this.tasks.forEach(task => {

      index = index + 1;
      if (task.id == taskId) {
        
        return;

      }
    });

    this.tasks.splice(index, 1, updatedTask);
    this.tasksUpdatedSource.next("tasksUpdated");
  }

  clear() : void {
    console.log("signedInUserService: CLEARING");
    this.user = null;
    this.tasks = null;
    sessionStorage.removeItem("session_token");
  }
}
