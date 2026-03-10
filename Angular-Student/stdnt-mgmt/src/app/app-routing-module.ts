import { NgModule } from '@angular/core';
import { pattern } from '@angular/forms/signals';
import { RouterModule, Routes } from '@angular/router';
import { Welcome } from './welcome/welcome';
import { Login } from './login/login';
import { Failure } from './failure/failure';
import { Dashboard } from './dashboard/dashboard';
import { roleGuard } from './guards/role-guard';

const routes: Routes = [
  {
    path: "",
    component: Welcome
  },
  {
    path:"login",
    component: Login
  },
  {
    path:"failure",
    component:Failure
  },
  {
    path:"welcome",
    component:Welcome
  },
  {
    path:"dashboard",
    component:Dashboard,
    canActivate: [roleGuard], //put guard here
    data:['Staff','Teacher']
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
