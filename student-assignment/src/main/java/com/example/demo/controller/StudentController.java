package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Show form
    @RequestMapping("/")
    public String showForm() {
        return "index.jsp";
    }


    // Handle submit
    @RequestMapping("/student")
    public String submitForm(Student student, Model model) {

        String result = studentService.saveToAll(student);

        switch (result) {
            case "SUCCESS_BOTH" ->
                model.addAttribute("message", "Saved to both H2 and PostgreSQL");
            case "SUCCESS_H2_ONLY" ->
                model.addAttribute("message", "Saved to H2 only");
            case "SUCCESS_PG_ONLY" ->
                model.addAttribute("message", "Saved to PostgreSQL only");
            default ->
                model.addAttribute("message", "Save failed");
        }

        return "index.jsp"; // reload same JSP
    }
}
