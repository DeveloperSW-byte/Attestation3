package ru.innopolis.spring.SpringProjectLibrary.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.spring.SpringProjectLibrary.dto.StudentDTO;
import ru.innopolis.spring.SpringProjectLibrary.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Операции с сущностью Student")
public class StudentRestController {

    private final StudentService studentService;

    @GetMapping
    @Operation(summary = "Получить список студентов")
    @ApiResponse(responseCode = "200", description = "Список успешно получен")
    public List<StudentDTO> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить студента по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Студент найден"),
            @ApiResponse(responseCode = "404", description = "Студент не найден")
    })
    public StudentDTO getById(@PathVariable Long id) {
        return studentService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
    }

    @PostMapping
    @Operation(summary = "Создать нового студента")
    @ApiResponse(responseCode = "201", description = "Студент создан")
    public void add(@RequestBody StudentDTO dto) {
        studentService.save(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить студента по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Студент удалён"),
            @ApiResponse(responseCode = "404", description = "Студент не найден") })
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}