package ru.innopolis.spring.SpringProjectLibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.spring.SpringProjectLibrary.controller.CourseController;
import ru.innopolis.spring.SpringProjectLibrary.dto.CourseDTO;
import ru.innopolis.spring.SpringProjectLibrary.service.CourseService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private CourseDTO testCourse;

    @BeforeEach
    void setUp() {
        testCourse = new CourseDTO();
        testCourse.setId(1L);
        testCourse.setTitle("Spring Boot");
        testCourse.setDescription("Intro to Spring");
        testCourse.setTeacherId(2L);
    }

    @Test
    void listCourses_shouldReturnCourseListView() throws Exception {
        when(courseService.getAllCourses()).thenReturn(List.of(testCourse));

        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/list"))
                .andExpect(model().attributeExists("courses"));
    }

    @Test
    void showAddCourseForm_shouldReturnAddView() throws Exception {
        mockMvc.perform(get("/courses/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/add"))
                .andExpect(model().attributeExists("course"));
    }

    @Test
    void addCourse_shouldRedirectToCourses() throws Exception {
        mockMvc.perform(post("/courses/add")
                        .param("title", "Spring Boot")
                        .param("description", "Intro to Spring")
                        .param("teacherId", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses"));

        verify(courseService, times(1)).save(any(CourseDTO.class));
    }

    @Test
    void viewCourse_shouldReturnCourseViewPage() throws Exception {
        when(courseService.getById(1L)).thenReturn(Optional.of(testCourse));

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/view"))
                .andExpect(model().attributeExists("course"));
    }

    @Test
    void deleteCourse_shouldRedirectToCourses() throws Exception {
        mockMvc.perform(post("/courses/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses"));

        verify(courseService, times(1)).delete(1L);
    }
}
