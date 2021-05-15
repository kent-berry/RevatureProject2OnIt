import { Injectable } from '@angular/core';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class SAMPLEUSERSService {


  userList : User[] = [
    new User("abc", "Bob", "Jenkins", "bob23", "1212", 2, 1, null, null),
    new User("abcd", "Sarah", "Jenkins", "sarah23", "1212", 2, 1, null, null),
    new User("abcde", "Tim", "Jenkins", "tim23", "1212", 2, 1, null, null),
    new User("abcdef", "Marge", "Jenkins", "marge23", "1212", 2, 1, null, null),
  ];

  constructor() { 

  }


  getUserList() : User[] {
    return this.userList;
  }
}
