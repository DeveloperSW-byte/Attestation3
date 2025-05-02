package ru.innopolis.spring.SpringProjectLibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.innopolis.spring.SpringProjectLibrary.dto.StudentDTO;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;
import ru.innopolis.spring.SpringProjectLibrary.repository.StudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepository = Mockito.mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @Test
    void testGetAllStudents() {
        Student student = Student.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivan@example.com")
                .build();

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        List<StudentDTO> result = studentService.getAllStudents();

        assertEquals(1, result.size());
        assertEquals("Ivan", result.get(0).getFirstName());
    }

    @Test
    void testGetById() {
        Student student = Student.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivan@example.com")
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<StudentDTO> result = studentService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Ivan", result.get().getFirstName());
    }

    @Test
    void testSaveStudent() {
        StudentDTO dto = new StudentDTO();
        dto.setFirstName("Anna");
        dto.setLastName("Petrova");
        dto.setEmail("anna@example.com");

        studentService.save(dto);

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testDeleteStudent() {
        studentService.delete(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}
