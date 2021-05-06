import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';
import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { AccountManagementPageComponent } from './account-management-page/account-management-page.component';
import { CreateTaskPageComponent } from './create-task-page/create-task-page.component';
import { EditTaskPageComponent } from './edit-task-page/edit-task-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    SignInPageComponent,
    UserHomePageComponent,
    RegisterPageComponent,
    AccountManagementPageComponent,
    CreateTaskPageComponent,
    EditTaskPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
