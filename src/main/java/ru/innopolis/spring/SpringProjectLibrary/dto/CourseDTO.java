package ru.innopolis.spring.SpringProjectLibrary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import ru.innopolis.spring.SpringProjectLibrary.model.Course;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CourseDTO {
    private Long id;

    @NotBlank(message = "Название обязательно")
    private String title;

    @NotBlank(message = "Описание обязательно")
    private String description;

    private Long teacherId;

    private TeacherDTO teacher;

    private List<Long> studentIds;
    private List<StudentDTO> students;

    public static CourseDTO fromEntity(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());

        if (course.getTeacher() != null) {
            dto.setTeacherId(course.getTeacher().getId());
            dto.setTeacher(TeacherDTO.fromEntity(course.getTeacher()));
        }

        if (course.getStudents() != null) {
            dto.setStudentIds(course.getStudents().stream()
                    .map(Student::getId)
                    .collect(Collectors.toList()));
            dto.setStudents(course.getStudents().stream()
                    .map(StudentDTO::fromEntity)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Course toEntity() {
        Course course = new Course();
        course.setId(this.id);
        course.setTitle(this.title);
        course.setDescription(this.description);
        // teacher and students set in service layer
        return course;
    }
}