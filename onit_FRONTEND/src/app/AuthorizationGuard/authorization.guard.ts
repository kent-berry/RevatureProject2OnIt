import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard implements CanActivate {

  constructor(
    private signedInUserService : SignedInUserService,
    private router: Router
  ) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
      // Simple authorization check, less secure:
      //  signedInUser != null --> AUTHORIZED
      // where signedInUser is assigned when logging in with valid info., and cleared when logging out

      console.log("----> AUTHORIZATION GUARD: localStorage.getItem(signedInUsername) = "+localStorage.getItem("signedInUsername"));
      console.log("----> AUTHORIZATION GUARD: localStorage.getItem(signedInUsername) = "+localStorage.getItem("signedInUsername"));
      console.log(localStorage.getItem("signedInUsername"));
      if (localStorage.getItem("signedInUsername")) {
        // A user is currently signed in
        console.log("   User currently signed in!");
        return true;
      } 
      else {
        this.router.navigate(['/home']);
        return false;
      }


      // More secure but less performant check
      //  Send http request to check authorization, if http_response.authorized == true --> AUTHORIZED
      
  }
  
}
