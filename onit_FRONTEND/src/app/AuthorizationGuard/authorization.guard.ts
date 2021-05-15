import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, NavigationEnd, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { filter } from 'rxjs/operators';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';

import { baseServerURL } from '../app.component';
import { User } from '../USER_RELATED_SERVICES/User';
import { HttpStuffService } from '../http-stuff.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard implements CanActivate {

  constructor(
    private signedInUserService : SignedInUserService,
    private router: Router,
    private httpStuffService: HttpStuffService
  ) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
    ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      console.log("AUTH GUARD");
      var authorized = false;
      

      if ( ! this.signedInUserService.user ) {
// test
        
        if (sessionStorage.getItem("session_token")) {
         
          /*  If a user has a session_token, they are automatically signed in
          */
          authorized = true;

          console.log("sessionKey  is not NOT NULL with value = "+sessionStorage.getItem("session_token"));
          
          this.httpStuffService.checkSessionAsync(sessionStorage.getItem("session_token").toString()).subscribe(
            (resp) =>
            {
              console.log(" * * * * * * * * * * AUTH GUARD --> : response to checkSessionAsync: "+resp);
              this.signedInUserService.user = resp;
              console.log(" * * * * * * * * * * AUTH GUARD --> : signedInUser.user.sessionKey: "+resp.sessionToken);
              if (this.signedInUserService.user != null)  {

              }

            }
          );  

        }
        else {
          console.log("session key is NULL")
          console.log(" XXXXX User NOT authorized");
        authorized = false;
        }
        
    
      }
      else {
        
   //     console.log("   User currently signed in!");
        console.log(" XXXXX User IS authorized");
        authorized = true;

      }

      console.log("   returning "+authorized)
      return authorized;
    
  }
  
}
