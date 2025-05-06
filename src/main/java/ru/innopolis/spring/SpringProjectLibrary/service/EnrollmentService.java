package ru.innopolis.spring.SpringProjectLibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.spring.SpringProjectLibrary.dto.EnrollmentDTO;
import ru.innopolis.spring.SpringProjectLibrary.model.Course;
import ru.innopolis.spring.SpringProjectLibrary.model.Enrollment;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;
import ru.innopolis.spring.SpringProjectLibrary.repository.CourseRepository;
import ru.innopolis.spring.SpringProjectLibrary.repository.EnrollmentRepository;
import ru.innopolis.spring.SpringProjectLibrary.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<EnrollmentDTO> getAll() {
        return enrollmentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EnrollmentDTO> getById(Long id) {
        return enrollmentRepository.findById(id).map(this::toDTO);
    }

    public void save(EnrollmentDTO dto) {
        Enrollment enrollment = toEntity(dto);
        enrollmentRepository.save(enrollment);
    }

    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }

    private EnrollmentDTO toDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());

        // добавим имена для отображения
        dto.setStudentName(enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName());
        dto.setCourseTitle(enrollment.getCourse().getTitle());

        return dto;
    }

    private Enrollment toEntity(EnrollmentDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setId(dto.getId());
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());
        enrollment.setStatus(dto.getStatus());

        return enrollment;
    }
}
