package edu.itstep.project.controller;

import edu.itstep.project.dto.GradeInDTO;
import edu.itstep.project.dto.GradeOutDTO;
import edu.itstep.project.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public ResponseEntity<GradeOutDTO> getGradeById(@PathVariable Long id) {
        GradeOutDTO grade = gradeService.getGradeById(id);
        return ResponseEntity.ok(grade);
    }

    @PostMapping
    public ResponseEntity<Void> createGrade(@Valid @RequestBody GradeInDTO gradeInDTO) {
        gradeService.createGrade(gradeInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGrade(@Valid @RequestBody GradeInDTO gradeInDTO, @PathVariable Long id) {
        gradeService.updateGrade(gradeInDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
