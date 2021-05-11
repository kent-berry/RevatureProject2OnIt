import { Injectable } from '@angular/core';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class SAMPLEUSERSService {


  userList : User[] = [
    new User(1, "Bob", "Jenkins", "bob23", "1212", 2, 1, null),
    new User(2, "Sarah", "Jenkins", "sarah23", "1212", 2, 1, null),
    new User(3, "Tim", "Jenkins", "tim23", "1212", 2, 1, null),
    new User(4, "Marge", "Jenkins", "marge23", "1212", 2, 1, null),
  ];

  constructor() { 

  }


  getUserList() : User[] {
    return this.userList;
  }
}
