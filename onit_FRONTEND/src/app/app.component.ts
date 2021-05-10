import { Component } from '@angular/core';
import { SignedInUserService } from './USER_RELATED_SERVICES/signed-in-user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  title = 'onit';
  
  constructor(
    private signedInUserService : SignedInUserService 
  ) 
  {

  }


  getSignedInUser() {
    return this.signedInUserService.signedInUsername;
  }

  signUserOut() {
    this.signedInUserService.clear();
  }

}
