import { Injectable } from '@angular/core';
import * as EventEmitter from 'events';
import { BehaviorSubject } from 'rxjs';
import { Task } from '../TASK_SERVICE/Task';
import { User } from './User';


@Injectable({
  providedIn: 'root'
})
export class SignedInUserService {

  private _user : User;
  private _tasks : Task[];

  tasksUpdatedSource = new BehaviorSubject<string>("tasksUpdated");
  tasksUpdated$ = this.tasksUpdatedSource.asObservable();


  


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
    
    this.tasks.push(task);
  }

  removeTask(task: Task) {
    
  }

  clear() : void {
    console.log("signedInUserService: CLEARING");
    this.user = null;
    this.tasks = [ ];
    sessionStorage.removeItem("session_token");
  }
}
