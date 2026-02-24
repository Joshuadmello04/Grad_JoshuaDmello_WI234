package com.example.demo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@SpringBootTest
class StudentServiceTest {

    @Mock
    private StudentRepository repo;

    @InjectMocks
    private StudentService service;

    @Test
    void testGetStudent_ServiceFound() {
        Student student = new Student(1L, "Joshua", "ABC", 5);

        when(repo.findById(1L))
                .thenReturn(Optional.of(student));

        Optional<Student> result = service.getStudent(1L);

        assertTrue(result.isPresent());
        assertEquals("Joshua", result.get().getName());
    }
}