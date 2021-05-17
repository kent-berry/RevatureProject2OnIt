import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { HttpStuffService } from '../http-stuff.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  form: FormGroup;

  responseMessage;



  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private httpStuffService : HttpStuffService
  ) {
  }

    registerButtonClicked(): void {

      class RegistrationInfo {
        firstName: string;
        lastName: string;
        email: string;
        password: string;
        password2: string;
        reminders: number;
      }
      
      var registrationInfo: RegistrationInfo = this.form.value;
      
      if (registrationInfo.firstName == "" || registrationInfo.lastName == "" || registrationInfo.email == "" || registrationInfo.password == "") {
        this.responseMessage = "Please do not leave any fields blank";
        return;
      }
      else if ( ! registrationInfo.email.includes("@")) {
        this.responseMessage = "Please enter a valid email address";
        return;
      }
      //console.log(registrationInfo.password2);

      this.httpStuffService.registerRequest(registrationInfo.firstName, registrationInfo.lastName, registrationInfo.email, registrationInfo.password).subscribe(
          resp =>
          {
            if (resp == null) {
              //console.log("Register: Username is taken");
              this.responseMessage = "That email is already in use";
            }
            else {
              //console.log("Register SUCCESS!");
              this.router.navigate(['/home']);
            }

          }
        
      )
  
     }

  ngOnInit(): void {
    this.form= this.formbuilder.group({
      firstName:[""],
      lastName:[""],
      email:[""],
      password:[""],
      password2:[""],
      reminders:[""]
    })

  }

}
