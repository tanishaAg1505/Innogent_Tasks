package com.assignment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.assignment.DTO.CourseRequestDTO;
import com.assignment.DTO.CourseResponseDTO;
import com.assignment.model.Course;
import com.assignment.Service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @PostMapping
    public CourseResponseDTO create(@RequestBody CourseRequestDTO req) {
        return courseService.create(req);
        		}

    @PutMapping("/{id}/instructor")
    public Course updateInstructor(@PathVariable int id, @RequestParam String instructor) {
        return courseService.updateInstructor(id, instructor);
    }

    @GetMapping("/with-count")
    public List<CourseResponseDTO> getCoursesWithStudentCount() {
        return courseService.getAllCoursesWithCount();
    }

    @GetMapping("/without-students")
    public List<Course> getCoursesWithoutStudents() {
        return courseService.getCoursesWithoutStudents();
    }

    @GetMapping("/top")
    public List<CourseResponseDTO> getTopNCourses(@RequestParam int n) {
        return courseService.getTopNCourses(n);
    }
}