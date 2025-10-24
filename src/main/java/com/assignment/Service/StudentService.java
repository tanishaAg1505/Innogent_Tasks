package com.assignment.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.DTO.StudentRequestDTO;
import com.assignment.DTO.StudentResponseDTO;
import com.assignment.model.Student;
import com.assignment.model.Course;
import com.assignment.repository.StudentRepository;
import com.assignment.repository.CourseRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Add student
    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Student student = new Student(dto.getName(), dto.getEmail(), dto.getCity(), course);
        Student savedStudent = studentRepository.save(student);
        return convertToResponse(savedStudent);
    }

    // Get all students
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.getAllStudentsWithCourse()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Update student
    public StudentResponseDTO updateStudent(int id, StudentRequestDTO dto) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setCity(dto.getCity());
        if (dto.getCourseId() != 0) {
            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            s.setCourse(course);
        }
        Student updated = studentRepository.save(s);
        return convertToResponse(updated);
    }

    // Delete student
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    // Get students by course name
    public List<StudentResponseDTO> getStudentsByCourseName(String courseName) {
        return studentRepository.findByCourseName(courseName)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get students without any course
    public List<StudentResponseDTO> getStudentsWithoutCourse() {
        return studentRepository.getStudentsWithoutCourse()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Search students by city and instructor
    public List<StudentResponseDTO> searchStudents(String city, String instructor) {
        return studentRepository.searchByCityAndInstructor(city, instructor)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Helper method to convert entity -> DTO
    private StudentResponseDTO convertToResponse(Student s) {
        return new StudentResponseDTO(
                s.getId(),
                s.getName(),
                s.getEmail(),
                s.getCity(),
                s.getCourse() != null ? s.getCourse().getC_name() : null
        );
    }
}