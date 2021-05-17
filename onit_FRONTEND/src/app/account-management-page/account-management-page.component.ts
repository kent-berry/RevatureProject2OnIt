import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { HttpStuffService } from '../http-stuff.service';
import { SignedInUserService } from '../USER_RELATED_SERVICES/signed-in-user.service';




@Component({
  selector: 'app-account-management-page',
  templateUrl: './account-management-page.component.html',
  styleUrls: ['./account-management-page.component.css']
})
export class AccountManagementPageComponent implements OnInit {

  constructor(
    private _signedInUserService: SignedInUserService,
    private _httpServiceStuff: HttpStuffService,
    private router: Router,
    public dialog: MatDialog
 
  ) { 

  }



  

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '380px',
      
    });

    dialogRef.afterClosed().subscribe(result => {
      //console.log('Dialog box closed!');
    });
  }


 



  ngOnInit(): void {
  }

  get signedInUserService() {
    return this._signedInUserService
  }

  
}


















@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'account-management-dialog.html',
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    public signedInUserService: SignedInUserService,
    public httpStuffService: HttpStuffService,
    public router : Router
    ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  deleteAccountClicked() {

    this.httpStuffService.deleteAccount(this.signedInUserService.user.email, this.signedInUserService.user.password, this.signedInUserService.user.sessionToken).subscribe(
      resp => {
        //console.log(" delete account, response receoved, Farewell!");
        
        this.signedInUserService.clear();
        this.router.navigate(['/home']);
      }
    )
    this.dialogRef.close();
  }

}
