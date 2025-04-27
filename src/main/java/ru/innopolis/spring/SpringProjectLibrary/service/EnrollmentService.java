package ru.innopolis.spring.SpringProjectLibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.spring.SpringProjectLibrary.repository.EnrollmentRepository;
import ru.innopolis.spring.SpringProjectLibrary.model.Enrollment;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
