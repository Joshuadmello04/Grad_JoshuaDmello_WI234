package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService service;

    @Test
    void testGetStudentSuccess() throws Exception {

        Student s = new Student();
        s.setRegNo(1L);
        s.setName("John");

        when(service.getStudent(1L))
                .thenReturn(Optional.of(s));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetStudentNotFound() throws Exception {

        when(service.getStudent(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }
}
