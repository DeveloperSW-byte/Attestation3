package ru.innopolis.spring.SpringProjectLibrary.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.innopolis.spring.SpringProjectLibrary.dto.CourseDTO;
import ru.innopolis.spring.SpringProjectLibrary.model.Course;
import ru.innopolis.spring.SpringProjectLibrary.model.Teacher;
import ru.innopolis.spring.SpringProjectLibrary.repository.CourseRepository;
import ru.innopolis.spring.SpringProjectLibrary.repository.TeacherRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        teacherRepository = mock(TeacherRepository.class);
        courseService = new CourseService(courseRepository, teacherRepository);
    }

    @Test
    void testGetAllCourses() {
        Course course1 = Course.builder().id(1L).title("Java").description("desc").teacher(new Teacher()).build();
        Course course2 = Course.builder().id(2L).title("Spring").description("desc").teacher(new Teacher()).build();

        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        List<CourseDTO> result = courseService.getAllCourses();

        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getTitle());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testSaveCourse() {
        Teacher teacher = Teacher.builder().id(1L).build();
        CourseDTO dto = new CourseDTO();
        dto.setId(1L);
        dto.setTitle("New Course");
        dto.setDescription("New Desc");
        dto.setTeacherId(1L);

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        courseService.save(dto);

        verify(courseRepository, times(1)).save(any(Course.class));
    }
}
