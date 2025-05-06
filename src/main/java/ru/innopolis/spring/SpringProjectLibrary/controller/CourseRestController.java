package ru.innopolis.spring.SpringProjectLibrary.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.spring.SpringProjectLibrary.dto.CourseDTO;
import ru.innopolis.spring.SpringProjectLibrary.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Управление курсами")
@RequiredArgsConstructor
public class CourseRestController {

    private final CourseService courseService;

    @GetMapping
    @Operation(summary = "Получить список всех курсов")
    @ApiResponse(responseCode = "200", description = "Список курсов успешно получен")
    public List<CourseDTO> getAll() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить курс по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Курс найден"),
            @ApiResponse(responseCode = "404", description = "Курс не найден")})
    public CourseDTO getById(@PathVariable Long id) {
        return courseService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));
    }

    @PostMapping
    @Operation(summary = "Создать курс")
    @ApiResponse(responseCode = "201", description = "Курс создан")
    public void add(@RequestBody CourseDTO dto) {
        courseService.save(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить курс по ID")
    @ApiResponse(responseCode = "204", description = "Курс удален")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}