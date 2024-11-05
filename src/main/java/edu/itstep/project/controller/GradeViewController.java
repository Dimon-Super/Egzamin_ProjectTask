package edu.itstep.project.controller;

import edu.itstep.project.dto.GradeInDTO;
import edu.itstep.project.dto.GradeOutDTO;
import edu.itstep.project.dto.GradeUpdateDTO;
import edu.itstep.project.exception.GradeNotFoundException;
import edu.itstep.project.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        System.out.println("showGradesPage method called");
        model.addAttribute("students", gradeService.getAllStudents());
        model.addAttribute("subjects", gradeService.getAllSubjects());
        model.addAttribute("grades", gradeService.getAllGrades());
        return "list";
    }

    @GetMapping("/filter")
    public String filterGrades(@RequestParam(required = false) Long subjectId,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               Model model) {
        model.addAttribute("students", gradeService.getAllStudents());
        model.addAttribute("subjects", gradeService.getAllSubjects());
        model.addAttribute("teachers", gradeService.getAllTeachers());
        model.addAttribute("grades", gradeService.filterGrades(subjectId, date));
        return "list";
    }

    @GetMapping("/create")
    public String showCreateGradePage(Model model) {
        model.addAttribute("students", gradeService.getAllStudents());
        model.addAttribute("subjects", gradeService.getAllSubjects());
        model.addAttribute("teachers", gradeService.getAllTeachers()); // Додайте цей рядок

        return "createGrade";
    }

    @PostMapping("/create")
    public String createGrade(@ModelAttribute @Valid GradeInDTO gradeInDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "createGrade";
        }

        gradeService.createGrade(gradeInDTO);
        redirectAttributes.addFlashAttribute("message", "Оцінка успішно додана!");
        return "redirect:/grades/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditGradePage(@PathVariable("id") Long id, Model model) {
        System.out.println("Editing grade with ID: " + id);
        GradeOutDTO gradeToEdit = gradeService.getGradeById(id);

        if (gradeToEdit == null) {
            System.out.println("Grade not found, redirecting to list");
            return "redirect:/grades/list";
        }

        model.addAttribute("gradeToEdit", gradeToEdit);
        model.addAttribute("students", gradeService.getAllStudents());
        model.addAttribute("subjects", gradeService.getAllSubjects());
        model.addAttribute("teachers", gradeService.getAllTeachers());
        return "editGrade";
    }

    @PostMapping("/update/{id}")
    public String updateGrade(@PathVariable("id") Long id,
                              @ModelAttribute @Valid GradeUpdateDTO gradeUpdateDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "editGrade";
        }

        gradeService.updateGrade(gradeUpdateDTO, id);
        redirectAttributes.addFlashAttribute("message", "Оцінка успішно оновлена!");
        return "redirect:/grades/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteGrade(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            gradeService.deleteGrade(id);
            redirectAttributes.addFlashAttribute("message", "Оцінка успішно видалена!");
        } catch (GradeNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/grades/list";
    }

}
