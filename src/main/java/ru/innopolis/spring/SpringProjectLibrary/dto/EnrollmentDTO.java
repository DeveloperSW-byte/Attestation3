package ru.innopolis.spring.SpringProjectLibrary.dto;

import lombok.Data;
import ru.innopolis.spring.SpringProjectLibrary.model.Course;
import ru.innopolis.spring.SpringProjectLibrary.model.Enrollment;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;

import java.time.LocalDate;

@Data
public class EnrollmentDTO {

    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentDate;
    private String status;

    private String studentName;
    private String courseTitle;

    public static EnrollmentDTO fromEntity(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setEnrollmentDate(enrollment.getEnrolledAt());
        dto.setStatus(enrollment.getStatus());

        Student student = enrollment.getStudent();
        if (student != null) {
            dto.setStudentId(student.getId());
            dto.setStudentName(student.getFirstName() + " " + student.getLastName());
        }

        Course course = enrollment.getCourse();
        if (course != null) {
            dto.setCourseId(course.getId());
            dto.setCourseTitle(course.getTitle());
        }

        return dto;
    }

    public Enrollment toEntity(Student student, Course course) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(this.id);
        enrollment.setEnrolledAt(this.enrollmentDate);
        enrollment.setStatus(this.status);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollment;
    }
}

