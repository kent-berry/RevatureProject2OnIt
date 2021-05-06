import { Injectable } from '@angular/core';
import { SignedInUser } from './SignedInUser';

@Injectable({
  providedIn: 'root'
})
export class SignedInUserService {

  private _signedInUser : SignedInUser;

  constructor() {

   }


   get signedInUser() {
     return this._signedInUser
   }
   set signedInUser(signedInUser: SignedInUser) {
     this._signedInUser = signedInUser;
   }
  
}
