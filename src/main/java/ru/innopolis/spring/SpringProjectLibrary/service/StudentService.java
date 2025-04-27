package ru.innopolis.spring.SpringProjectLibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.spring.SpringProjectLibrary.repository.StudentRepository;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findByOrgName(String orgName) {
        return studentRepository.findByOrgName(orgName);
    }
}