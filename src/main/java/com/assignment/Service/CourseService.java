package com.assignment.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.assignment.DTO.CourseRequestDTO;
import com.assignment.DTO.CourseResponseDTO;
import com.assignment.model.Course;
import com.assignment.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    
    public CourseResponseDTO create(CourseRequestDTO req) {
        Course c = new Course();
        c.setC_name(req.getName());
        c.setC_instructor(req.getInstructor());
        courseRepository.save(c);

        CourseResponseDTO resp = new CourseResponseDTO();
        resp.setId(c.getC_id());
        resp.setName(c.getC_name());
        resp.setInstructor(c.getC_instructor());
        resp.setTotalStudents(0);

        return resp;
    }

    // Update course instructor by ID
    public Course updateInstructor(int courseId, String instructor) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setC_instructor(instructor);
        return courseRepository.save(course);
    }

    // Get all courses with student count
    public List<CourseResponseDTO> getAllCoursesWithCount() {
        return courseRepository.getAllCoursesGrouped()
                .stream()
                .map(c -> new CourseResponseDTO(
                        c.getC_id(),
                        c.getC_name(),
                        c.getC_details(),
                        c.getC_instructor(),
                        c.getStudent() != null ? c.getStudent().size() : 0))
                .collect(Collectors.toList());
    }

    // Courses without students
    public List<Course> getCoursesWithoutStudents() {
        return courseRepository.getCoursesWithoutStudents();
    }

    // Top N courses by enrollment
    public List<CourseResponseDTO> getTopNCourses(int n) {
        return courseRepository.getTopCourses(PageRequest.of(0, n))
                .stream()
                .map(c -> new CourseResponseDTO(
                        c.getC_id(),
                        c.getC_name(),
                        c.getC_details(),
                        c.getC_instructor(),
                        c.getStudent() != null ? c.getStudent().size() : 0))
                .collect(Collectors.toList());
    }

	
}