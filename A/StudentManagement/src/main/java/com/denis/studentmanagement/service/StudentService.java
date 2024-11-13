package com.denis.studentmanagement.service;
import com.denis.studentmanagement.model.Student;
import com.denis.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, Student updatedStudent) {
        if (studentRepository.existsById(studentId)) {
            updatedStudent.setStudentId(studentId);
            return studentRepository.save(updatedStudent);
        } else {
            throw new RuntimeException("Student not found with ID " + studentId);
        }
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
