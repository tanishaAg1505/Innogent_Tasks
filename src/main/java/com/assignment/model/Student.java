package com.assignment.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String city;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Student() {}

    public Student(String name, String email, String city, Course course) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.course = course;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
