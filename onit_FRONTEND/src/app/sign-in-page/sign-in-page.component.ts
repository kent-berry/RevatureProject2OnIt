import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';
import { User } from '../USER_RELATED_SERVICES/User';
import { filter } from 'rxjs/operators';
import { SAMPLEUSERSService } from '../USER_RELATED_SERVICES/sampleusers.service';
import { FormBuilder, FormGroup } from '@angular/forms';



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

  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private signedInUserService: SignedInUserService,
    private sampleUsersService: SAMPLEUSERSService,
  ) { 
      

  }

  submitLogin(): void {

    const fromValue = this.form.value;
    console.log(fromValue);

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
      console.log("SIGNINPAGE CONSTRUCTOR: this.signedInUserService.signedInUser = "+this.signedInUserService.signedInUser);
      if (this.signedInUserService.signedInUser) {
        this.router.navigate(['/tasks']);
      }
      
  }

}
