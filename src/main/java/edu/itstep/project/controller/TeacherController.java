package edu.itstep.project.controller;

import edu.itstep.project.dto.TeacherDTO;
import edu.itstep.project.service.TeacherService;
import edu.itstep.project.service.GradeService;
import edu.itstep.project.service.StudentService;
import edu.itstep.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }
}
