import { Injectable } from '@angular/core';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class SignedInUserService {

  private _signedInUser : User;

  constructor() {

   }


   get signedInUser() {
     return this._signedInUser
   }
   set signedInUser(signedInUser: User) {
     this._signedInUser = signedInUser;
   }
  
}
