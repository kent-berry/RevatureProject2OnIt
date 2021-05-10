import { Injectable } from '@angular/core';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class SignedInUserService {

  private _signedInUsername : string;

  constructor() {

   }


   get signedInUsername() {
     return localStorage.getItem("signedInUsername");
   }
   set signedInUsername(signedInUsername: string) {
     this._signedInUsername = signedInUsername;
   }
  
}
