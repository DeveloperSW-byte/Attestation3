package ru.innopolis.spring.SpringProjectLibrary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.spring.SpringProjectLibrary.dto.TeacherDTO;
import ru.innopolis.spring.SpringProjectLibrary.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public String listTeachers(Model model) {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers/list"; // шаблон: templates/teachers/list.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        return "teachers/add"; // шаблон: templates/teachers/add.html
    }

    @PostMapping("/add")
    public String addTeacher(@ModelAttribute("teacher") @Valid TeacherDTO teacherDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teacher", teacherDto);
            return "teachers/add";
        }

        teacherService.save(teacherDto.toEntity());
        return "redirect:/teachers";
    }

    @GetMapping("/{id}")
    public String viewTeacher(@PathVariable Long id, Model model) {
        TeacherDTO teacher = teacherService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher ID: " + id));
        model.addAttribute("teacher", teacher);
        return "teachers/view"; // шаблон: templates/teachers/view.html
    }

    @PostMapping("/{id}/delete")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return "redirect:/teachers";
    }
}