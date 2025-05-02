package ru.innopolis.spring.SpringProjectLibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.spring.SpringProjectLibrary.dto.TeacherDTO;
import ru.innopolis.spring.SpringProjectLibrary.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(TeacherDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<TeacherDTO> getById(Long id) {
        return teacherRepository.findById(id)
                .map(TeacherDTO::fromEntity);
    }

    public void save(TeacherDTO dto) {
        teacherRepository.save(dto.toEntity());
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
