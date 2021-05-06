import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';
import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { AccountManagementPageComponent } from './account-management-page/account-management-page.component';

@NgModule({
  declarations: [
    AppComponent,
    SignInPageComponent,
    UserHomePageComponent,
    RegisterPageComponent,
    AccountManagementPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
