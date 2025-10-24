package com.assignment.repository;

import com.assignment.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    // Courses with total students (entity list, counting students done in service)
    @Query("SELECT c FROM Course c LEFT JOIN c.student s GROUP BY c")
    List<Course> getAllCoursesGrouped();

    // Courses without any student
    @Query("SELECT c FROM Course c LEFT JOIN c.student s WHERE s IS NULL")
    List<Course> getCoursesWithoutStudents();

    // Top N courses by student enrollment (entities)
    @Query("SELECT c FROM Course c LEFT JOIN c.student s GROUP BY c ORDER BY COUNT(s) DESC")
    List<Course> getTopCourses(Pageable pageable);
}