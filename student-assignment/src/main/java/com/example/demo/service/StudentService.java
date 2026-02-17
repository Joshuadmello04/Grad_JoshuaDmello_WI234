package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.h2.StudentH2Repository;
import com.example.demo.repository.postgres.StudentPostgresRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentService {

    @Autowired
    private StudentH2Repository h2Repo;

    @Autowired
    private StudentPostgresRepository postgresRepo;

    public String saveToAll(Student student) {

        boolean h2Success = false;
        boolean pgSuccess = false;

        // ── Save to H2 ────────────────────────
        try {
            Student h2Student = new Student(
                    student.getName(),
                    student.getEmail(),
                    student.getAge(),
                    student.getCourse()
            );
            h2Repo.save(h2Student);
            h2Success = true;
            System.out.println("✅ Saved to H2");
        } catch (Exception e) {
            System.out.println("❌ H2 failed: " + e.getMessage());
        }

        // ── Save to PostgreSQL ─────────────────
        try {
            Student pgStudent = new Student(
                    student.getName(),
                    student.getEmail(),
                    student.getAge(),
                    student.getCourse()
            );
            postgresRepo.save(pgStudent);
            pgSuccess = true;
            System.out.println("✅ Saved to PostgreSQL");
        } catch (Exception e) {
            System.out.println("❌ PostgreSQL failed: " + e.getMessage());
        }

        if (h2Success && pgSuccess) return "SUCCESS_BOTH";
        if (h2Success)              return "SUCCESS_H2_ONLY";
        if (pgSuccess)              return "SUCCESS_PG_ONLY";
        return "FAILED_BOTH";
    }
}
