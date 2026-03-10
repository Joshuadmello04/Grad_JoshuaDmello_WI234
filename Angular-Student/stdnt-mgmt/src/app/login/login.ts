import { Component } from '@angular/core';
import { Role } from '../services/role';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  constructor(private rs:Role,private router:Router){
  }

   abc(event:any){
      event.preventDefault(); // to prevent default behaviour
      let uname:string = event.target.elements[0].value;
      console.log("Username "+uname)
      let pwd:string = event.target.elements[1].value;
      console.log("Password : "+pwd);
      let role:string = event.target.elements[2].value;
      console.log("Role : "+role)
      console.log("------------------------")

      if(uname==pwd){
        this.rs.setRole(role);
        this.router.navigate(['dashboard']);
      }
      else{
        this.router.navigate(['failure'])
     }
    }
}
