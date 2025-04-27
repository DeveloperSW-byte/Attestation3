package ru.innopolis.spring.SpringProjectLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.spring.SpringProjectLibrary.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
