package ru.innopolis.spring.SpringProjectLibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.spring.SpringProjectLibrary.dto.CourseDTO;
import ru.innopolis.spring.SpringProjectLibrary.model.Course;
import ru.innopolis.spring.SpringProjectLibrary.model.Teacher;
import ru.innopolis.spring.SpringProjectLibrary.repository.CourseRepository;
import ru.innopolis.spring.SpringProjectLibrary.repository.StudentRepository;
import ru.innopolis.spring.SpringProjectLibrary.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<CourseDTO> getById(Long id) {
        return courseRepository.findById(id)
                .map(CourseDTO::fromEntity);
    }

    public void save(CourseDTO dto) {
        Course course = dto.toEntity();

        // Привязка преподавателя
        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid teacher ID: " + dto.getTeacherId()));
            course.setTeacher(teacher);
        }

        // Привязка студентов
        if (dto.getStudentIds() != null && !dto.getStudentIds().isEmpty()) {
            course.setStudents(studentRepository.findAllById(dto.getStudentIds()));
        }

        courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}