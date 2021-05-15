import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';
import { User } from '../USER_RELATED_SERVICES/User';
import { filter } from 'rxjs/operators';
import { SAMPLEUSERSService } from '../USER_RELATED_SERVICES/sampleusers.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Task } from '../TASK_SERVICE/Task';
import { HttpStuffService } from '../http-stuff.service';



@Component({
  selector: 'app-home',
  templateUrl: './sign-in-page.component.html',
  styleUrls: ['./sign-in-page.component.css']
})
export class SignInPageComponent implements OnInit {
    returnUrl: string;

    // taking user input from the form
  previousUrl: string;
  form: FormGroup;

  loadingSymbolActive : boolean;


  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private signedInUserService: SignedInUserService,
    private httpStuffService: HttpStuffService,
    private sampleUsersService: SAMPLEUSERSService,
  ) { 
      

  }

  submitLogin(): void {


    this.loadingSymbolActive = true;

    class Credentials {
      email : string;
      password : string;
    }
    var credentials : Credentials = this.form.value;

    if (credentials.email == "" || credentials.password == "") {
      console.log("Blank credentials");
    }

    console.log(credentials.email+", "+credentials.password);
    

    console.log("Sign in Page: submitLogin START!");

    this.httpStuffService.logInRequest(credentials.email,credentials.password).subscribe(
      response => {
        console.log("SignInPage: submitLogIn() --> response received!");
        if (!response) {

          this.loadingSymbolActive = false;
          // Could not validate login credentials
          //    Do not navigate
          //    Print a message

        }
        else {
          
          // User successfully logged in!

          this.loadingSymbolActive = false;

          console.log("submitLogin(): response != null, and it is " + response.email +" with session token = "+response.sessionToken);
          
          this.signedInUserService.user = response;
          sessionStorage.setItem("session_token", this.signedInUserService.user.sessionToken);  // In the event of a browser refresh, this is important


          
            
          this.router.navigate(['/tasks']);

        }
      }

    );
    

    



   }

   ngOnInit(): void{

    this.form= this.formbuilder.group({
      email:[""],
      password:[""]
    }) 
    this.router.events
    .pipe(filter(event => event instanceof NavigationEnd))
    .subscribe((event: NavigationEnd) => {
      console.log('prev:', event.url);
      this.previousUrl = event.url;
    });
      // If already signed in, redirect to User Home
      
      if (this.signedInUserService.user) {
        this.router.navigate(['/tasks']);
      }
      
  }

}
