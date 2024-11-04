package edu.itstep.project.controller;

import edu.itstep.project.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/grades")
public class GradeViewController {

    private final GradeService gradeService;
    @Autowired
    public GradeViewController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/list")
    public String showGradesPage(Model model) {
        model.addAttribute("students", gradeService.getAllStudents());
        model.addAttribute("subjects", gradeService.getAllSubjects());
        model.addAttribute("grades", gradeService.getAllGrades());
        return "list";
    }
}
