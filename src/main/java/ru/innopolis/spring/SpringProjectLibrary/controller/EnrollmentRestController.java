package ru.innopolis.spring.SpringProjectLibrary.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.spring.SpringProjectLibrary.dto.EnrollmentDTO;
import ru.innopolis.spring.SpringProjectLibrary.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@Tag(name = "Enrollments", description = "Управление зачислениями")
@RequiredArgsConstructor
public class EnrollmentRestController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    @Operation(summary = "Получить список всех зачислений")
    @ApiResponse(responseCode = "200", description = "Список зачислений получен")
    public List<EnrollmentDTO> getAll() {
        return enrollmentService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить зачисление по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Зачисление найдено"),
            @ApiResponse(responseCode = "404", description = "Зачисление не найдено")
    })
    public EnrollmentDTO getById(@PathVariable Long id) {
        return enrollmentService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid enrollment ID: " + id));
    }

    @PostMapping
    @Operation(summary = "Создать зачисление")
    @ApiResponse(responseCode = "201", description = "Зачисление создано")
    public void add(@RequestBody EnrollmentDTO dto) {
        enrollmentService.save(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить зачисление")
    @ApiResponse(responseCode = "204", description = "Зачисление удалено")
    public void delete(@PathVariable Long id) {
        enrollmentService.delete(id);
    }
}
