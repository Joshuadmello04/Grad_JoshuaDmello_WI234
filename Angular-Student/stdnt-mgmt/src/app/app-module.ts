import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Login } from './login/login';
import { Welcome } from './welcome/welcome';
import { Role } from './services/role';
import { Failure } from './failure/failure';
import { Dashboard } from './dashboard/dashboard';
import { Student } from './services/student';
import { FormsModule } from '@angular/forms';
import { Menu } from './menu/menu';

@NgModule({
  declarations: [App, Login, Welcome, Failure, Dashboard, Menu],
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  providers: [provideBrowserGlobalErrorListeners(), Role, Student],
  bootstrap: [App],
})
export class AppModule {}
