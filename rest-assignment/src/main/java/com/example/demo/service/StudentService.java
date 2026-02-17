package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	public StudentRepository repo;
	
	//get /students
	public List<Student> getAllStudents()
	{
		return repo.findAll();
	}
	
	//get /students/{regno}
	 public Student getStudent(Long regNo) {
	        return repo.findById(regNo)
	                .orElseThrow(() -> new RuntimeException("Student not found"));
	    }
	
	//post /students
	public Student createStudent(Student st)
	{
		return repo.save(st);
	}
	
	
	//put /students/{regno}
	public Student updateStudent(Long regNo, Student updated)
	{
		Student existing = getStudent(regNo);
		
		existing.setRollNo(updated.getRollNo());
        existing.setName(updated.getName());
        existing.setStandard(updated.getStandard());
        existing.setSchool(updated.getSchool());
        existing.setGender(updated.getGender());
        existing.setPercentage(updated.getPercentage());
        
        return repo.save(existing);	
	}
	
	//patch /students/{regno}
	public Student patchUpdate(Long regNo,Map<String, Object> updates)
	{
		Student s = getStudent(regNo);
		
		 updates.forEach((k, v) -> {
	            switch (k) {
	                case "name" -> s.setName((String) v);
	                case "school" -> s.setSchool((String) v);
	                case "percentage" -> s.setPercentage(Double.valueOf(v.toString()));
	                case "standard" -> s.setStandard(Integer.valueOf(v.toString()));
	            }
	        });
		 return repo.save(s);
	}
	
	//delete /students/regno
	public void deleteByRegNo(Long regNo)
	{
		repo.deleteById(regNo);
	}
	
	public List<Student> getBySchoolName(String name)
	{
		return repo.findBySchoolIgnoreCase(name);
	}
	
	public long countBySchool(String name)
	{
		return repo.countBySchool(name);
	}
	
	public long getCountBySchoolStd(String name,long std)
	{
		return repo.countBySchoolAndStandard(name, std);
	}
	
	public List<Student> findByResult(Boolean pass)
	{
		return repo.findByResult(pass);
	}
	
	public long countByGenderAndStandard(String gender,long std)
	{
		return repo.countByGenderAndStandard(gender, std);
	}
}
