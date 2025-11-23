package com.assignment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.assignment.DTO.StudentRequestDTO;
import com.assignment.DTO.StudentResponseDTO;
import com.assignment.Service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
 
    // Create student
    @PostMapping
    public StudentResponseDTO addStudent(@RequestBody StudentRequestDTO dto) {
        return studentService.addStudent(dto);
    }

    // Get all students
    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Update student
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(@PathVariable int id, @RequestBody StudentRequestDTO dto) {
        return studentService.updateStudent(id, dto);
    }

    // Delete student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    // Get students by course name
    @GetMapping("/by-course")
    public List<StudentResponseDTO> getStudentsByCourse(@RequestParam String courseName) {
        return studentService.getStudentsByCourseName(courseName);
    }

    // Get students without course
    @GetMapping("/without-course")
    public List<StudentResponseDTO> getStudentsWithoutCourse() {
        return studentService.getStudentsWithoutCourse();
    }

    // Search students by city and instructor
    @GetMapping("/search")
    public List<StudentResponseDTO> searchStudents(@RequestParam String city, @RequestParam String instructor) {
        return studentService.searchStudents(city, instructor);
    }
}