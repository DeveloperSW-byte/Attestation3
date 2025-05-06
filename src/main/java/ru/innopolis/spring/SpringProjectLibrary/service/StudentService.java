package ru.innopolis.spring.SpringProjectLibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.spring.SpringProjectLibrary.dto.StudentDTO;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;
import ru.innopolis.spring.SpringProjectLibrary.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<StudentDTO> getById(Long id) {
        return studentRepository.findById(id)
                .map(this::toDTO);
    }

    public void save(StudentDTO dto) {
        studentRepository.save(toEntity(dto));
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone()); // добавлено
        dto.setOrgName(student.getOrgName()); // добавлено
        return dto;
    }

    private Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone()); // добавлено
        student.setOrgName(dto.getOrgName()); // добавлено
        return student;
    }
}