import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountManagementPageComponent } from './account-management-page/account-management-page.component';
import { AuthorizationGuard } from './AuthorizationGuard/authorization.guard';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { CreateTaskPageComponent } from './create-task-page/create-task-page.component';
import { EditTaskPageComponent } from './edit-task-page/edit-task-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: SignInPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'tasks', component: UserHomePageComponent, canActivate: [AuthorizationGuard] },    // the '/tasks' url will have 2+ table views of to-do items
  { path: 'accountmanagement', component: AccountManagementPageComponent, canActivate: [AuthorizationGuard] },
  { path: 'createtask', component: CreateTaskPageComponent, canActivate: [AuthorizationGuard] },
  { path: 'editviewtask', component: EditTaskPageComponent, canActivate: [AuthorizationGuard] } 
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
