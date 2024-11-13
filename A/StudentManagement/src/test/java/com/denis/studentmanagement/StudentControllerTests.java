package com.denis.studentmanagement;

import com.denis.studentmanagement.model.Student;
import com.denis.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddStudent() throws Exception {
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"studentId\": 1, \"name\": \"John Doe\", \"age\": 20, \"sexuality\": \"M\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        testAddStudent();
        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Updated\", \"age\": 21, \"sexuality\": \"M\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
    }
}
