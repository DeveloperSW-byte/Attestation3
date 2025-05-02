package ru.innopolis.spring.SpringProjectLibrary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EnrollmentDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentDate;

}
