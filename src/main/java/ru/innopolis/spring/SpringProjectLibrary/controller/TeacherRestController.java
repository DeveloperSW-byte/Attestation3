package ru.innopolis.spring.SpringProjectLibrary.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.spring.SpringProjectLibrary.dto.TeacherDTO;
import ru.innopolis.spring.SpringProjectLibrary.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teachers", description = "Управление преподавателями")
@RequiredArgsConstructor
public class TeacherRestController {

    private final TeacherService teacherService;

    @GetMapping
    @Operation(summary = "Получить список всех преподавателей")
    @ApiResponse(responseCode = "200", description = "Список успешно получен")
    public List<TeacherDTO> getAll() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить преподавателя по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Преподаватель найден"),
            @ApiResponse(responseCode = "404", description = "Преподаватель не найден")
    })
    public TeacherDTO getById(@PathVariable Long id) {
        return teacherService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher ID: " + id));
    }

    @PostMapping
    @Operation(summary = "Создать преподавателя")
    @ApiResponse(responseCode = "201", description = "Преподаватель создан")
    public void add(@RequestBody TeacherDTO dto) {
        teacherService.save(dto.toEntity());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить преподавателя")
    @ApiResponse(responseCode = "204", description = "Преподаватель удален")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }
}