import { Component, OnInit } from '@angular/core';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';

@Component({
  selector: 'app-account-management-page',
  templateUrl: './account-management-page.component.html',
  styleUrls: ['./account-management-page.component.css']
})
export class AccountManagementPageComponent implements OnInit {

  constructor(
    private _signedInUserService: SignedInUserService
  ) { 

  }

  ngOnInit(): void {
  }

  get signedInUserService() {
    return this._signedInUserService
  }

}
