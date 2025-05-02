package ru.innopolis.spring.SpringProjectLibrary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.innopolis.spring.SpringProjectLibrary.model.Course;

@Data
public class CourseDTO {
    private Long id;

    @NotBlank(message = "Название обязательно")
    private String title;

    @NotBlank(message = "Описание обязательно")
    private String description;

    private Long teacherId;

    public static CourseDTO fromEntity(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setTeacherId(course.getTeacher().getId());
        return dto;
    }

    public Course toEntity() {
        Course course = new Course();
        course.setId(this.id);
        course.setTitle(this.title);
        course.setDescription(this.description);
        // Установку teacher нужно делать в сервисе, по ID
        return course;
    }
}
