import { Component } from '@angular/core';
import { Role } from '../services/role';
import { Router } from '@angular/router';
import student from '../models/student';
import { Student } from '../services/student';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {

  students:student[] =[]
  role:string=""
  //we put this to show form on off
  showForm:boolean = false

  //student ka object
  newStudent:student ={
    rollNo: 0,
    name: "",
    regNo: "",
    schoolName: ""
  }

  constructor(private rs:Role, private ss:Student){
    this.students = this.ss.getStudents()
    this.role = this.rs.getRole()
  }
  
  delete(rollNo:number){
    this.ss.deleteStudent(rollNo);
    this.students=this.ss.getStudents();//update
  }
  
  addStudent(){
    this.ss.addStudent({...this.newStudent}); //we used the ... spread operator
    this.students = this.ss.getStudents();//update
    //after adding clear the form
    this.newStudent={
      rollNo:0,
      name:"",
      regNo:"",
      schoolName:""
    }
     this.showForm = false
  }

  editMode:boolean = false
  editingRollNo:number = 0
  //for editing we keep these 2
  edit(rollNo:number){
    const student = this.students.find(s => s.rollNo === rollNo)

    if(student){ //if exist then toggle the boolean
      this.newStudent = {...student}
      this.editMode = true
      this.editingRollNo = rollNo
      this.showForm = true
    }
  }
  updateStudent(){
    
    this.ss.updateStudent(this.editingRollNo,this.newStudent)
    this.students = this.ss.getStudents()
    this.editMode = false
    this.showForm = false

    this.newStudent = {
      rollNo:0,
      name:"",
      regNo:"",
      schoolName:""
    }
  }
}
function updateStudent() {
  throw new Error('Function not implemented.');
}

