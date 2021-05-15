import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from './TASK_SERVICE/Task';
import { SignedInUserService } from './USER_RELATED_SERVICES/signed-in-user.service';
import { User } from './USER_RELATED_SERVICES/User';
import { baseServerURL } from './app.component';
import { LocalDate } from './DATES/LocalDate';


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

   // createTaskRequest returns Observable<Task> where the Task returned is the task created (on server side)
   createTaskRequest(taskName: string, Notes: string, dueDate: LocalDate, label: string, reminder: number, repeatable: boolean,
                       latitude : number, longitude : number) : Observable<Task> {
    
      var task: Task = new Task("placeholder", "placeholder", taskName, Notes, dueDate, label, null, null, reminder, repeatable, latitude, longitude);
      
      return this.http.post<Task>(baseServerURL + "/api/task", task, this.httpOptionsJSON);

   }

   // updateTaskRequest returns Observable<Task> where the Task returned is the updated version of the task
   updateTaskRequest(taskName: string, Notes: string, dueDate: LocalDate, label: string, reminder: number, repeatable: boolean,
                      latitude : number, longitude : number) : Observable<Task> {
    
    var task: Task = new Task("placeholder", "placeholder", taskName, Notes, dueDate, label, null, null, reminder, repeatable, latitude, longitude);

    return this.http.put<Task>(baseServerURL + "/api/task", task, this.httpOptionsJSON);

 }







 // fetch a users tasks
 requestUserTasks(u: User) : Observable<Task[]> {

      var dtoUserSessionKey = {"id": u.id, "email": u.email, "sessionToken": u.sessionToken};

      var userLoginDTO = {"email": "dude", "password": "cool"};

      var tasks: Observable<Task[]> =  this.http.post<Task[]>(baseServerURL + "/viewTasks", dtoUserSessionKey, this.httpOptionsJSON);
      return tasks;
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
