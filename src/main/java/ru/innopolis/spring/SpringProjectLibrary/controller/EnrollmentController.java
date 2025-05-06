package ru.innopolis.spring.SpringProjectLibrary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.spring.SpringProjectLibrary.dto.EnrollmentDTO;
import ru.innopolis.spring.SpringProjectLibrary.service.CourseService;
import ru.innopolis.spring.SpringProjectLibrary.service.EnrollmentService;
import ru.innopolis.spring.SpringProjectLibrary.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    @GetMapping
    public String listEnrollments(Model model) {
        List<EnrollmentDTO> enrollments = enrollmentService.getAll();
        model.addAttribute("enrollments", enrollments);
        return "enrollments/list"; // templates/enrollments/list.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new EnrollmentDTO());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "enrollments/add";
    }

    @PostMapping("/add")
    public String addEnrollment(@ModelAttribute("enrollment") @Valid EnrollmentDTO enrollmentDto,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("courses", courseService.getAllCourses());
            return "enrollments/add";
        }

        enrollmentService.save(enrollmentDto);
        return "redirect:/enrollments";
    }

    @GetMapping("/{id}")
    public String viewEnrollment(@PathVariable Long id, Model model) {
        EnrollmentDTO enrollment = enrollmentService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid enrollment ID: " + id));
        model.addAttribute("enrollment", enrollment);
        return "enrollments/view"; // templates/enrollments/view.html
    }

    @PostMapping("/{id}/delete")
    public String deleteEnrollment(@PathVariable Long id) {
        enrollmentService.delete(id);
        return "redirect:/enrollments";
    }
}
