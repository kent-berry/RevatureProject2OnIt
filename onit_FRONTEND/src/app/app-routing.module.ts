import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountManagementPageComponent } from './account-management-page/account-management-page.component';
import { AuthorizationGuard } from './AuthorizationGuard/authorization.guard';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { CreateTaskPageComponent } from './create-task-page/create-task-page.component';
import { EditTaskPageComponent } from './edit-task-page/edit-task-page.component';
import { TaskStatsPageComponent } from './task-stats-page/task-stats-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: SignInPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'tasks', component: UserHomePageComponent, canActivate: [AuthorizationGuard] },
  { path: 'accountmanagement', component: AccountManagementPageComponent, canActivate: [AuthorizationGuard] },
  { path: 'createtask', component: CreateTaskPageComponent, canActivate: [AuthorizationGuard] },
  { path: 'editviewtask/:taskId', component: EditTaskPageComponent, canActivate: [AuthorizationGuard] },
  { path: 'taskstats', component: TaskStatsPageComponent, canActivate: [AuthorizationGuard] }  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
