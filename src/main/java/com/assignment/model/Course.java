package com.assignment.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;

    private String c_name;
    private String c_details;
    private String c_instructor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Student> student;

    public Course() {}

    public Course(String c_name, String c_details, String c_instructor) {
        this.c_name = c_name;
        this.c_details = c_details;
        this.c_instructor = c_instructor;
    }

    // Getters and setters
    public int getC_id() { return c_id; }
    public void setC_id(int c_id) { this.c_id = c_id; }

    public String getC_name() { return c_name; }
    public void setC_name(String c_name) { this.c_name = c_name; }

    public String getC_details() { return c_details; }
    public void setC_details(String c_details) { this.c_details = c_details; }

    public String getC_instructor() { return c_instructor; }
    public void setC_instructor(String c_instructor) { this.c_instructor = c_instructor; }

    public List<Student> getStudent() { return student; }
    public void setStudent(List<Student> student) { this.student = student; }
}