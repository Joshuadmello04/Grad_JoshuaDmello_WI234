package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repo;

    @InjectMocks
    private StudentService service;

    @Test
    void testStudentFound() {

        Student s = new Student();
        s.setRegNo(1L);
        s.setName("John");

        when(repo.findById(1L))
                .thenReturn(Optional.of(s));

        Optional<Student> result = service.getStudent(1L);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
    }

    @Test
    void testStudentNotFound() {

        when(repo.findById(1L))
                .thenReturn(Optional.empty());

        Optional<Student> result = service.getStudent(1L);

        assertFalse(result.isPresent());
    }
}
