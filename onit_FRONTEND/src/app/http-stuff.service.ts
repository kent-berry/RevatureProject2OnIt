import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from './TASK_SERVICE/Task';
import { SignedInUserService } from './USER_RELATED_SERVICES/signed-in-user.service';
import { User } from './USER_RELATED_SERVICES/User';

import { LocalDate } from './DATES/LocalDate';

// EC2 version
export let baseServerURL = "http://ec2-3-129-11-214.us-east-2.compute.amazonaws.com:9000/OnIt";

@Injectable({
  providedIn: 'root'
})
export class HttpStuffService {

  

  httpHeaders: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  
  
  httpOptionsJSON = { headers: this.httpHeaders, withCredentials: true };


  
  //httpHeaders: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  
  
//  httpOptionsJSON = { headers: this.httpHeaders, withCredentials: true };
  
   

  constructor(private signedInUserService: SignedInUserService,
              private http: HttpClient
              ) {

    
   }


   // registerRequest returns Observable<User>
   registerRequest(firstName: string, lastName: string, email: string, password: string) {
     var userRegisterDTO = {"email": email, "password": password, "firstname": firstName, "lastname": lastName};
     return this.http.post<User>(baseServerURL + "/register", userRegisterDTO, this.httpOptionsJSON);
   }



   // logInRequest returns Observable<User> where the User returned is a fully filled in User (all their info, including their list of tasks)
   logInRequest(email: string, password: string) : Observable<User> {

      // Send in the POST body a partially filled in User (email and password)
      var userLoginDTO = {"email": email, "password": password};


      var obsv: Observable<User> =  this.http.post<User>(baseServerURL + "/login", userLoginDTO, this.httpOptionsJSON);
      return obsv;
        
   }

   logOutRequest(u : User) {

      
      var userDTO =  {"id": u.id, "firstName": u.firstName, "lastName": u.lastName, "email": u.email, "password": u.password
      , "receiveEmailReminders": u.receiveEmailReminders, "goal": u.goal, "sessionToken": u.sessionToken,
   "accountCreatedMonth": getMonthFromString(u.accountCreated.month), "accountCreatedDay": u.accountCreated.dayOfMonth, "accountCreatedYear": u.accountCreated.year};

      
      
    return this.http.post<string>(baseServerURL + "/logout", userDTO, this.httpOptionsJSON);
    
   }

   // returns a new copy of all user tasks
   createTaskRequest(taskName: string, Notes: string, dueDate: LocalDate, label: string, reminder: number, repeatable: boolean,
                       latitude : number, longitude : number, u: User) : Observable<Task> {
    
         var dueDateYear = null;
         var dueDateMonth = null;
         var dueDateDay = null;
         if (dueDate) {
            dueDateYear = dueDate.year;
            dueDateMonth = dueDate.monthValue;
            dueDateDay = dueDate.dayOfMonth;
         }
         var taskDTO = {"taskName": taskName, "notes": Notes, "dueDateMonth": dueDateMonth, "dueDateDay": dueDateDay, "dueDateYear": dueDateYear,
            "reminder": reminder, "repeatable": repeatable, "taskLabel": label, "latitude": latitude, "longitude": longitude,
            "userId":u.id, "sessionToken":u.sessionToken};

            console.log("Here is taskDTO before http addTask request:");
         printObj(taskDTO);
      
         return this.http.post<Task>(baseServerURL + "/addTask", taskDTO, this.httpOptionsJSON);

   }



     
     deleteTaskRequest(task: Task, user: User) : Observable<Task[]> {
      
      var updatedTaskDTO = {"id": task.id, "userId": user.id, "sessionToken": user.sessionToken, "taskName": task.taskName, "notes": task.notes,
      "dueDateMonth": null,  "dueDateDay" : null, "dueDateYear": null,
      "createdMonth": null, "createdDay" : null, "createdYear": null, 
      "completedMonth": null, "completedDay" : null, "completedYear": null, 
      "reminder":task.reminder, "repeatable":task.repeatable, "taskLabel_fk":task.taskLabel_fk,
      "latitude": task.latitude, "longitude": task.longitude,
      
      };

      console.log("DELETING!!!!");
      printObj(updatedTaskDTO)

      return this.http.post<Task[]>(baseServerURL + "/deleteTask", updatedTaskDTO, this.httpOptionsJSON);

 }



   

   // returns a FULL copy of the updated task
   updateTaskRequest(task: Task, user: User) : Observable<Task[]> {
      var dueDateYear = null;
      var dueDateMonth = null;
      var dueDateDay = null;
      if (task.dueDate) {
         dueDateYear = task.dueDate.year;
         dueDateMonth = task.dueDate.monthValue;
         dueDateDay = task.dueDate.dayOfMonth;
      }
      var createdYear = null;
      var createdMonth = null;
      var createdDay = null;
      if (task.dateCreated) {
         createdYear = task.dateCreated.year;
         createdMonth = task.dateCreated.monthValue;
         createdDay = task.dateCreated.dayOfMonth;
      }
      var completedYear = null;
      var completedMonth = null;
      var completedDay = null;
      if (task.dateCompleted) {
         completedYear = task.dateCompleted.year;
         completedMonth = task.dateCompleted.monthValue;
         completedDay = task.dateCompleted.dayOfMonth;
      }
      var updatedTaskDTO = {"id": task.id, "userId": user.id, "sessionToken": user.sessionToken, "taskName": task.taskName, "notes": task.notes,
      "dueDateMonth": dueDateMonth,  "dueDateDay" : dueDateDay, "dueDateYear": dueDateYear,
      "createdMonth": createdMonth, "createdDay" : createdDay, "createdYear": createdYear, 
      "completedMonth": completedMonth, "completedDay" : completedDay, "completedYear": completedYear, 
      "reminder":task.reminder, "repeatable":task.repeatable, "taskLabel_fk":task.taskLabel_fk,
      "latitude": task.latitude, "longitude": task.longitude,
      
      };

      console.log("UPDATING!!!!");
      printObj(updatedTaskDTO)

      return this.http.post<Task[]>(baseServerURL + "/updateTask", updatedTaskDTO, this.httpOptionsJSON);

 }







 // fetch a users tasks
 requestUserTasks(u: User) : Observable<Task[]> {

      if (!u) {
         return;
      }
      var dtoUserSessionKey = {"id": u.id, "email": u.email, "sessionToken": u.sessionToken};

      var userLoginDTO = {"email": "dude", "password": "cool"};

      var tasks: Observable<Task[]> =  this.http.post<Task[]>(baseServerURL + "/viewTasks", dtoUserSessionKey, this.httpOptionsJSON);
      return tasks;
 }


requestDownloadData(u: User) : Observable<any> {


      
      var userDTO =  {"id": u.id, "firstName": u.firstName, "lastName": u.lastName, "email": u.email, "password": u.password
      , "receiveEmailReminders": u.receiveEmailReminders, "goal": u.goal,
   "accountCreatedMonth": getMonthFromString(u.accountCreated.month), "accountCreatedDay": u.accountCreated.dayOfMonth, "accountCreatedYear": u.accountCreated.year,
   "sessionToken": u.sessionToken};
   console.log("                       PRINTING HTTP DOWNLOADDATA INPUT:")
   printObj(userDTO);

      
      
   return this.http.post<string>(baseServerURL + "/downloadMyData", userDTO, this.httpOptionsJSON);


}




 // check session async
 
 checkSessionAsync(sessionToken: string) : Observable<User> {

  
  return this.http.get<User>(baseServerURL + "/checkActiveSession",  {   params: new HttpParams().set('sessionToken', sessionToken),
                                                                   withCredentials: true});

 }

 /*
	private String id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate accountCreated;
	private int receiveEmailReminders;  
	private int goal;
	
	String sessionToken;
 */


 convertUserObj(u: User) {
    return {"id": u.id, "firstName": u.firstName, "lastName": u.lastName, "email": u.email, "password": u.password,
     "accountCreated": u.accountCreated, "receiveEmailReminders": u.receiveEmailReminders, "goal": u.goal, "sessionToken": u.sessionToken};
 }
}

function getMonthFromString(mon){
   return new Date(Date.parse(mon +" 1, 2012")).getMonth()+1
}

function printObj(obj) {
   Object.keys(obj).forEach((prop)=> console.log(prop+":  "+obj[prop]));
}