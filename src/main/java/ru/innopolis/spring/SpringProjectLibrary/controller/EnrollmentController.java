package ru.innopolis.spring.SpringProjectLibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.spring.SpringProjectLibrary.dto.EnrollmentDTO;
import ru.innopolis.spring.SpringProjectLibrary.model.Course;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;
import ru.innopolis.spring.SpringProjectLibrary.repository.CourseRepository;
import ru.innopolis.spring.SpringProjectLibrary.repository.StudentRepository;
import ru.innopolis.spring.SpringProjectLibrary.service.EnrollmentService;

import java.util.List;

@Controller
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("enrollments", enrollmentService.getAll());
        return "enrollments/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new EnrollmentDTO());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        return "enrollments/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute EnrollmentDTO enrollmentDTO) {
        enrollmentService.save(enrollmentDTO);
        return "redirect:/enrollments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        enrollmentService.delete(id);
        return "redirect:/enrollments";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        EnrollmentDTO enrollment = enrollmentService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("enrollment", enrollment);
        return "enrollments/view";
    }
}
