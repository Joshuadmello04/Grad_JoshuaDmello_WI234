package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	 // 200 OK
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.getAllStudents());
    }
	
	//testing assignment
	// 200 OK / 404
    @GetMapping("/{reg}")
    public ResponseEntity<Student> getOne(@PathVariable Long reg) {
        return ResponseEntity.ok(service.getStudent(reg));
    }
	
	 // 201 CREATED
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student s) {
        Student created = service.createStudent(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

	  // 200 OK
    @PutMapping("/{reg}")
    public ResponseEntity<Student> update(@PathVariable Long reg,
                                          @RequestBody Student s) {
        return ResponseEntity.ok(service.updateStudent(reg, s));
    }

    // 204 NO CONTENT
    @DeleteMapping("/{reg}")
    public ResponseEntity<Void> delete(@PathVariable Long reg) {
        service.deleteByRegNo(reg);
        return ResponseEntity.noContent().build();
    }

//	@GetMapping("/students/{reg}")
//	public Optional<Student> getOne(@PathVariable long reg)
//	{
//		return service.getStudent(reg);
//	}
	
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student s)
	{
		//this will take json
		return service.createStudent(s);
	}
	
//	@PutMapping("/students/{reg}")
//	public Student updateStudent(@PathVariable long reg,@RequestBody Student s)
//	{
//		//path + json
//		return service.updateStudent(reg, s);
//	}
//	
//	@PatchMapping("/students/{reg}")
//	public Student patchUpdate(@PathVariable long reg,@RequestBody Map<String, Object> updates)
//	{
//		 return service.patchUpdate(reg, updates);
//	}
	
	
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
