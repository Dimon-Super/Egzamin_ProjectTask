package edu.itstep.project.controller;

import edu.itstep.project.dto.StudentDTO;
import edu.itstep.project.service.GradeService;
import edu.itstep.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentViewController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}/grades")
    public String showStudentGrades(@PathVariable Long id,
                                    @RequestParam(required = false) Long subjectId,
                                    @RequestParam(required = false) LocalDate date,
                                    Model model) {
        model.addAttribute("grades", gradeService.getGradesByStudentId(id, subjectId, date));
        model.addAttribute("subjects", gradeService.getAllSubjects());


        StudentDTO student = studentService.getStudentById(id);
        model.addAttribute("student", student);

        model.addAttribute("studentId", id);
        return "studentGrades";
    }


}
