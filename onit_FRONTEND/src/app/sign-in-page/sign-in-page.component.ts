import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';
import { User } from '../USER_RELATED_SERVICES/User';
import { filter } from 'rxjs/operators';
import { SAMPLEUSERSService } from '../USER_RELATED_SERVICES/sampleusers.service';



@Component({
  selector: 'app-home',
  templateUrl: './sign-in-page.component.html',
  styleUrls: ['./sign-in-page.component.css']
})
export class SignInPageComponent implements OnInit {

  previousUrl: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private signedInUserService: SignedInUserService,
    private sampleUsersService: SAMPLEUSERSService
  ) { 

    
    router.events
  .pipe(filter(event => event instanceof NavigationEnd))
  .subscribe((event: NavigationEnd) => {
    console.log('prev:', event.url);
    this.previousUrl = event.url;
  });


    // If already signed in, redirect to User Home
    if (signedInUserService.signedInUser) {
      this.router.navigate(['/userhome']);
    }
  }

  ngOnInit(): void {
  }

  submitLogIn(): void {

    console.log("HOME-PAGE: log in button pressed.");
    console.log("Test printing all sample users: ");
    this.sampleUsersService.getUserList().forEach(function(value) {
      console.log(value.username);
      console.log(value.password);
    })

    this.signedInUserService.signedInUser = new User("bob10", "pw");

    this.router.navigate(['/tasks'])

  }

}
