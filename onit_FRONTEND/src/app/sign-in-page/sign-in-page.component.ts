import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { SignedInUserService } from '../SignedInUserService/signed-in-user.service';
import { SignedInUser } from '../SignedInUserService/SignedInUser';
import { filter } from 'rxjs/operators';



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
    private signedInUserService: SignedInUserService
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

     this.signedInUserService.signedInUser = new SignedInUser("bob10", "pw");

    this.router.navigate(['/tasks'])

  }

}
