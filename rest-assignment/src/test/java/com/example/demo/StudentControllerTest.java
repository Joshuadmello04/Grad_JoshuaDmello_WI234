package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService service;

    // -----------------------------
    // GET ALL STUDENTS (200 OK)
    // -----------------------------
    @Test
    void testGetAllStudents() throws Exception {

        List<Student> students = Arrays.asList(
                new Student(1L, "Joshua", "ABC", 5),
                new Student(2L, "Maria", "XYZ", 6)
        );

        when(service.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    // -----------------------------
    // GET ONE - FOUND (200 OK)
    // -----------------------------
    @Test
    void testGetStudent_Found() throws Exception {

        Student student = new Student(1L, "Joshua", "ABC", 5);

        when(service.getStudent(1L))
                .thenReturn(Optional.of(student));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Joshua"));
    }

    // -----------------------------
    // GET ONE - NOT FOUND (404)
    // -----------------------------
    @Test
    void testGetStudent_NotFound() throws Exception {

        when(service.getStudent(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }

    // -----------------------------
    // CREATE STUDENT (201 CREATED)
    // -----------------------------
    @Test
    void testCreateStudent() throws Exception {

        Student student = new Student(1L, "Joshua", "ABC", 5);

        when(service.createStudent(any(Student.class)))
                .thenReturn(student);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "reg": 1,
                            "name": "Joshua",
                            "school": "ABC",
                            "standard": 5
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Joshua"));
    }

    // -----------------------------
    // UPDATE - NOT FOUND (404)
    // -----------------------------
    @Test
    void testUpdateStudent_NotFound() throws Exception {

        when(service.updateStudent(eq(1L), any(Student.class)))
                .thenReturn(null);

        mockMvc.perform(put("/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Updated"
                        }
                        """))
                .andExpect(status().isNotFound());
    }

    // -----------------------------
    // PATCH - SUCCESS (200 OK)
    // -----------------------------
    @Test
    void testPatchStudent() throws Exception {

        Student updated = new Student(1L, "Updated", "ABC", 5);

        when(service.patchUpdate(eq(1L), any(HashMap.class)))
                .thenReturn(updated);

        mockMvc.perform(patch("/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Updated"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    // -----------------------------
    // DELETE (204 NO CONTENT)
    // -----------------------------
    @Test
    void testDeleteStudent() throws Exception {

        doNothing().when(service).deleteByRegNo(1L);

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
    }

    // -----------------------------
    // GET BY SCHOOL (200 OK)
    // -----------------------------
    @Test
    void testGetBySchoolName() throws Exception {

        List<Student> students = Arrays.asList(
                new Student(1L, "Joshua", "ABC", 5)
        );

        when(service.getBySchoolName("ABC"))
                .thenReturn(students);

        mockMvc.perform(get("/students/school")
                .param("name", "ABC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    // -----------------------------
    // COUNT BY SCHOOL (200 OK)
    // -----------------------------
    @Test
    void testCountBySchool() throws Exception {

        when(service.countBySchool("ABC")).thenReturn(5L);

        mockMvc.perform(get("/students/school/count")
                .param("name", "ABC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(5));
    }

    // -----------------------------
    // GET STRENGTH (200 OK)
    // -----------------------------
    @Test
    void testGetStrength() throws Exception {

        when(service.countByGenderAndStandard("Male", 5L))
                .thenReturn(10L);

        mockMvc.perform(get("/students/strength")
                .param("gender", "Male")
                .param("std", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10));
    }
}