package ru.innopolis.spring.SpringProjectLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Пример кастомного метода:
    List<Student> findByOrgName(String orgName);
}
