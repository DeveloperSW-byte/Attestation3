package ru.innopolis.spring.SpringProjectLibrary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.spring.SpringProjectLibrary.dto.StudentDTO;
import ru.innopolis.spring.SpringProjectLibrary.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        List<StudentDTO> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/list"; // шаблон: src/main/resources/templates/students/list.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "students/add"; // шаблон: src/main/resources/templates/students/add.html
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") @Valid StudentDTO studentDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "students/add";
        }
        studentService.save(studentDto);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        StudentDTO student = studentService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "students/view"; // шаблон: src/main/resources/templates/students/view.html
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}