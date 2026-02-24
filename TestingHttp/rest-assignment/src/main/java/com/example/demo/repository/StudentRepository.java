package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 1. Get students by school
	List<Student> findBySchoolIgnoreCase(String school);

    // 2. Count students by school
    long countBySchool(String school);

    // 3. Count by school + standard
    long countBySchoolAndStandard(String school, long standard);

    // 4. Pass / Fail ordered by percentage DESC
    @Query("""
        SELECT s FROM Student s
        WHERE (:pass = true AND s.percentage >= 40)
           OR (:pass = false AND s.percentage < 40)
        ORDER BY s.percentage DESC
    """)
    List<Student> findByResult(@Param("pass") boolean pass);

    // 5. Count by gender + standard
    long countByGenderAndStandard(String gender, long standard);
}