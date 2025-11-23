package com.assignment.DTO;

public class CourseRequestDTO {
	private String name;
    private String instructor;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public CourseRequestDTO(String name, String instructor) {
		super();
		this.name = name;
		this.instructor = instructor;
	}
    
    
}
