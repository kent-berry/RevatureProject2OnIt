import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from './TASK_SERVICE/Task';
import { SignedInUserService } from './USER_RELATED_SERVICES/signed-in-user.service';
import { User } from './USER_RELATED_SERVICES/User';

@Injectable({
  providedIn: 'root'
})
export class HttpStuffService {

  private http: HttpClient;

  private baseURL: string = "http://142.93.205.142:8090";
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private signedInUserService: SignedInUserService) {

    
   }


   // logInRequest returns Observable<User> where the User returned is a fully filled in User (all their info, including their list of tasks)
   logInRequest(username: string, password: string) : Observable<User> {

      // Send in the POST body a partially filled in User (email and password)
      var user: User = new User(null, null, null, 
                                username,password,
                                null, null, null) ;

      return this.http.post<User>(this.baseURL+"/login", user, this.httpOptions);
        
   }

   logOutRequest() {

   }

   // createTaskRequest returns Observable<Task> where the Task returned is the task created (on server side)
   // Passed in via the request body: 
  //    username
  //    password
  //    task
   createTaskRequest(userId: number, taskName: string, Notes: string, dueDate: Date, label: string, reminder: number, repeatable: boolean) : Observable<Task> {
    
      var task: Task = new Task(-1, userId, taskName, Notes, dueDate, label, new Date(), null, reminder, repeatable);
      
      // This is for authenticating the ability of a user to add a task to their list, handled server side
      var email: string = this.signedInUserService.signedInUsername;
      var password: string = this.signedInUserService.signedInPassword;

      return this.http.post<Task>(this.baseURL+"/api/task", [email, password, task], this.httpOptions);

   }

   // updateTaskRequest returns Observable<Task> where the Task returned is the updated version of the task
   // Passed in via the request body: 
  //    username
  //    password
  //    task
   updateTaskRequest(userId: number, taskName: string, Notes: string, dueDate: Date, label: string, reminder: number, repeatable: boolean) : Observable<Task> {
    
    var task: Task = new Task(-1, userId, taskName, Notes, dueDate, label, new Date(), null, reminder, repeatable);

    // This is for authenticating the ability of a user to add a task to their list, handled server side
    var email: string = this.signedInUserService.signedInUsername;
    var password: string = this.signedInUserService.signedInPassword;

    return this.http.put<Task>(this.baseURL+"/api/task", [email, password, task], this.httpOptions);

 }


}
