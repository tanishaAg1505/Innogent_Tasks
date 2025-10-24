package com.assignment.DTO;

public class StudentResponseDTO {
	private int id;
    private String name;
    private String email;
    private String city;
    private String courseName;

    public StudentResponseDTO(int id, String name, String email, String city, String courseName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.courseName = courseName;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCity() { return city; }
    public String getCourseName() { return courseName; }
}
