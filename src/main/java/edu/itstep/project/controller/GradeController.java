package edu.itstep.project.controller;

import edu.itstep.project.dto.GradeOutDTO;
import edu.itstep.project.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/teachers/grades")
@Validated
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public ResponseEntity<List<GradeOutDTO>> getAllGrades() {
        List<GradeOutDTO> grades = gradeService.getAllGrades();
        return ResponseEntity.ok(grades);
    }
}
