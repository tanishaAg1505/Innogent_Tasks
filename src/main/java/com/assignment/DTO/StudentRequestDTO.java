package com.assignment.DTO;

public class StudentRequestDTO {
	private String name;
    private String email;
    private String city;
    private int courseId;

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCity() { return city; }
    public int getCourseId() { return courseId; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCity(String city) { this.city = city; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
}
