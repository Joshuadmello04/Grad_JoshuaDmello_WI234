import { Component } from '@angular/core';
import { Role } from '../services/role';

@Component({
  selector: 'app-welcome',
  standalone: false,
  templateUrl: './welcome.html',
  styleUrl: './welcome.css',
})
export class Welcome {
    role:string = "Guest"
    //injection of service
    constructor(public rs:Role){
    }
}
