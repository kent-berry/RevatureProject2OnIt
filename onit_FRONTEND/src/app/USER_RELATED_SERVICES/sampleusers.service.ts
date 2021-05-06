import { Injectable } from '@angular/core';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class SAMPLEUSERSService {


  userList : User[] = [
    new User("bob23", "1212"),
    new User("sarah23", "1212"),
    new User("ben23", "1212"),
    new User("david23", "1212")
  ];

  constructor() { 

  }


  getUserList() : User[] {
    return this.userList;
  }
}
