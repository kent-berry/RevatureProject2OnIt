import { Component, HostListener } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpStuffService } from './http-stuff.service';
import { SignedInUserService } from './USER_RELATED_SERVICES/signed-in-user.service';

// EC2 version
//export let baseServerURL = "http://ec2-3-129-11-214.us-east-2.compute.amazonaws.com:9000/OnIt";

// Digital Ocean Droplet version
// export let baseServerURL = "http://142.93.205.142:8090/OnIt";

// Local
//export let baseServerURL = "http://localhost:9500/OnIt";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
 
  title = 'onit';
  subscription: Subscription;
    
  constructor(
    private _signedInUserService : SignedInUserService, 
    private router: Router,
    private httpStuffService: HttpStuffService
  ) 
  {

  }

  get signedInUserService() {
    return this._signedInUserService;
  }

  getSignedInUser() {
    
    //console.log("getSignedInUser(): this.signedInUserService.user = "+this.signedInUserService.user);
    return this.signedInUserService.user;
    
  }

  signUserOut() {

    this.signedInUserService.tasksWereFetched = false;
    
    this.httpStuffService.logOutRequest(this.signedInUserService.user).subscribe(
      response =>
      {
        
      }
    )
    
    this.signedInUserService.clear();
    this.router.navigate(['/home']);
    

  }

  ngOnInit() {
    //console.log("AppComponent created!");
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  
  
}
