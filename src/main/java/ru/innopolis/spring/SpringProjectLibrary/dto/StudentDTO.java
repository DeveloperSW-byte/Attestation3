package ru.innopolis.spring.SpringProjectLibrary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.innopolis.spring.SpringProjectLibrary.model.Student;

@Data
public class StudentDTO {
    private Long id;
    @NotBlank(message = "Имя обязательно")
    private String firstName;
    @NotBlank(message = "Фамилия обязательна")
    private String lastName;
    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязателен")
    private String email;
    private String phone;
    private String orgName;

    public static StudentDTO fromEntity(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setOrgName(student.getOrgName());
        return dto;
    }

    public Student toEntity() {
        Student student = new Student();
        student.setId(this.id);
        student.setFirstName(this.firstName);
        student.setLastName(this.lastName);
        student.setEmail(this.email);
        student.setPhone(this.phone);
        student.setOrgName(this.orgName);
        return student;
    }
}

