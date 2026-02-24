package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService service;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAll()
	{
		List<Student> students = service.getAllStudents();
		return ResponseEntity.ok(students);
	}
	
	@GetMapping("/students/{reg}")
	public ResponseEntity<Student> getOne(@PathVariable long reg)
	{
		Student student = service.getStudent(reg);
		if(student==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(student);
	}
	
	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student s)
	{
		//this will take json
		Student created =  service.createStudent(s);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping("/students/{reg}")
	public ResponseEntity<Student> updateStudent(@PathVariable long reg,@RequestBody Student s)
	{
		//path + json
		Student updated = service.updateStudent(reg, s);
		if(updated==null)
		{
			return ResponseEntity.notFound().build(); //201
		}
		return ResponseEntity.ok(updated);
	}
	
	@PatchMapping("/students/{reg}")
	public ResponseEntity<Student> patchUpdate(@PathVariable long reg,@RequestBody Map<String, Object> updates)
	{
		 Student s = service.patchUpdate(reg, updates);
		 if(s==null)
		 {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok(s);
	}
	
	
	@DeleteMapping("/students/{reg}")
	public ResponseEntity<Void> deleteStudent(@PathVariable long reg)
	{
		service.deleteByRegNo(reg);
		return ResponseEntity.noContent().build();
	}
	
	
	//get students by school name
	@GetMapping("/students/school")
	public ResponseEntity<List<Student>> getBySchoolName(@RequestParam String name)
	{
		// students/school?name= in url
		return ResponseEntity.ok(service.getBySchoolName(name));
	}
	
	//get count by schoolname
	@GetMapping("/students/school/count")
	public ResponseEntity<Long> getCountBySchool(@RequestParam String name)
	{
		return ResponseEntity.ok(service.countBySchool(name));
	}
	
	// get /students/school/standard/count?class=5&name=ABC
	@GetMapping("/students/school/standard/count")
	public long getCountBySchoolStd(@RequestParam String school,@RequestParam long std)
	{
		return service.getCountBySchoolStd(school, std);
	}
	
	//show pass and fail
	@GetMapping("/students/result")
	public ResponseEntity<List<Student>> getResults(@RequestParam boolean pass)
	{
		return ResponseEntity.ok(service.findByResult(pass));
	}
	
	//get strength using gender and school
	@GetMapping("/students/strength")
	public ResponseEntity<Long> getStrength(@RequestParam String gender,@RequestParam long std)
	{
		return ResponseEntity.ok(service.countByGenderAndStandard(gender, std));
	}
}
