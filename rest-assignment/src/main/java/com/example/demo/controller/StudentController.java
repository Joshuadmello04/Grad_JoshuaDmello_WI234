package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Student> getAll()
	{
		return service.getAllStudents();
	}
	
	@GetMapping("/students/{reg}")
	public Student getOne(@PathVariable long reg)
	{
		return service.getStudent(reg);
	}
	
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student s)
	{
		//this will take json
		return service.createStudent(s);
	}
	
	@PutMapping("/students/{reg}")
	public Student updateStudent(@PathVariable long reg,@RequestBody Student s)
	{
		//path + json
		return service.updateStudent(reg, s);
	}
	
	@PatchMapping("/students/{reg}")
	public Student patchUpdate(@PathVariable long reg,@RequestBody Map<String, Object> updates)
	{
		 return service.patchUpdate(reg, updates);
	}
	
	
	@DeleteMapping("/students/{reg}")
	public void deleteStudent(@PathVariable long reg)
	{
		service.deleteByRegNo(reg);
	}
	
	
	//get students by school name
	@GetMapping("/students/school")
	public List<Student> getBySchoolName(@RequestParam String name)
	{
		// students/school?name= in url
		return service.getBySchoolName(name);
	}
	
	//get count by schoolname
	@GetMapping("/students/school/count")
	public long getCountBySchool(@RequestParam String name)
	{
		return service.countBySchool(name);
	}
	
	// get /students/school/standard/count?class=5&name=ABC
	@GetMapping("/students/school/standard/count")
	public long getCountBySchoolStd(@RequestParam String school,@RequestParam long std)
	{
		return service.getCountBySchoolStd(school, std);
	}
	
	//show pass and fail
	@GetMapping("/students/result")
	public List<Student> getResults(@RequestParam boolean pass)
	{
		return service.findByResult(pass);
	}
	
	//get strength using gender and school
	@GetMapping("/students/strength")
	public Long getStrength(@RequestParam String gender,@RequestParam long std)
	{
		return service.countByGenderAndStandard(gender, std);
	}
}
