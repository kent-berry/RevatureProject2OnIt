import { Injectable } from '@angular/core';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class SignedInUserService {

  private _signedInUsername : string;
  private _signedInPassword : string;

  constructor() {

   }


   get signedInUsername() {
     return sessionStorage.getItem("signedInUsername");
   }
   set signedInUsername(signedInUsername: string) {
     sessionStorage.setItem("signedInUsername",signedInUsername);
   }

   get signedInPassword() {
    return sessionStorage.getItem("signedInPassword");
  }
   set signedInPassword(signedInPassword: string) {
    sessionStorage.setItem("signedInPassword",signedInPassword);
  }
  clear() : void {
    sessionStorage.clear();
  }
}
