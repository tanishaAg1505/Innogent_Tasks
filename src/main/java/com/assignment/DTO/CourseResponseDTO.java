package com.assignment.DTO;

public class CourseResponseDTO {
	private int id;
    private String name;
    private String details;
    private String instructor;
    private long totalStudents;

    public CourseResponseDTO(int id, String name, String details, String instructor, long totalStudents) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.instructor = instructor;
        this.totalStudents = totalStudents;
    }
    

	public CourseResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public void setTotalStudents(long totalStudents) {
		this.totalStudents = totalStudents;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getDetails() {
		return details;
	}


	public String getInstructor() {
		return instructor;
	}


	public long getTotalStudents() {
		return totalStudents;
	}
	
    
    
}
