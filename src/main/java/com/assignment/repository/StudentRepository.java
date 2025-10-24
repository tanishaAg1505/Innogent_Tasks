package com.assignment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.assignment.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Get all students with their course
    @Query("SELECT s FROM Student s JOIN FETCH s.course")
    List<Student> getAllStudentsWithCourse();

    // Get students by course name
    @Query("SELECT s FROM Student s JOIN s.course c WHERE c.c_name = :courseName")
    List<Student> findByCourseName(@Param("courseName") String courseName);

    // Get students without any course
    @Query("SELECT s FROM Student s WHERE s.course IS NULL")
    List<Student> getStudentsWithoutCourse();

    // Search students by city and instructor
    @Query("SELECT s FROM Student s JOIN s.course c WHERE s.city = :city AND c.c_instructor = :instructor")
    List<Student> searchByCityAndInstructor(@Param("city") String city, @Param("instructor") String instructor);
}