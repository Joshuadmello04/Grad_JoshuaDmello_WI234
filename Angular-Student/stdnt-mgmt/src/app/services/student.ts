import { Injectable } from '@angular/core';
import student from '../models/student';

@Injectable({
  providedIn: 'root',
})
export class Student {
  private students: student[] =[
    { rollNo: 1, name: "Rahul", regNo: "REG101", schoolName: "BSS" },
    { rollNo: 2, name: "Priya", regNo: "REG102", schoolName: "DAIS" },
    { rollNo: 3, name: "Arjun", regNo: "REG103", schoolName: "CJCSS" }
  ];

  getStudents(): student[]{
    return this.students;
  }

  addStudent(s:student){
    this.students.push(s);
  }

  deleteStudent(rollNo:number){
    this.students = this.students.filter(s => s.rollNo != rollNo);
  }

  updateStudent(rollNo:number, updatedStudent:student){
    const index = this.students.findIndex(s => s.rollNo === rollNo)
    if(index !== -1)
    {
      this.students[index] = updatedStudent
     }
  }
}
