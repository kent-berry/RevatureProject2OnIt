import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountManagementPageComponent } from './account-management-page/account-management-page.component';
import { AuthorizationGuard } from './AuthorizationGuard/authorization.guard';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { UserHomePageComponent } from './user-home-page/user-home-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: SignInPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'tasks', component: UserHomePageComponent, canActivate: [AuthorizationGuard] }, 
  { path: 'accountmanagement', component: AccountManagementPageComponent, canActivate: [AuthorizationGuard] }, 
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
