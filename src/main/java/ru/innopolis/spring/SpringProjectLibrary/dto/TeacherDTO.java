package ru.innopolis.spring.SpringProjectLibrary.dto;

import lombok.Data;
import ru.innopolis.spring.SpringProjectLibrary.model.Teacher;


@Data
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public static TeacherDTO fromEntity(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        return dto;
    }

    public Teacher toEntity() {
        Teacher teacher = new Teacher();
        teacher.setId(this.id);
        teacher.setFirstName(this.firstName);
        teacher.setLastName(this.lastName);
        teacher.setEmail(this.email);
        return teacher;
    }
}
