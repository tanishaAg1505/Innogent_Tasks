package Student_Management_System;


import exceptions.InvalidAgeException;
import exceptions.InvalidMarksException;

public class Student {
    private int id;
    private String name;
    private int classId;
    private int marks;
    private String gender;
    private int age;
    private String status; // Pass / Fail
    private int rank;InvalidAgeException

    public Student(int id, String name, int classId, int marks, String gender, int age) throws InvalidAgeException, InvalidMarksException {
        if(age > 20) throw new ("Age cannot be greater than 20: " + age);
        if(marks < 0 || marks > 100) throw new InvalidMarksException("Marks must be 0-100: " + marks);
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
        this.status = (marks >= 50) ? "Pass" : "Fail";
        this.rank = 0;
    }

    public boolean isAgeValid() { return age <= 20; }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getClassId() { return classId; }
    public int getMarks() { return marks; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public String getStatus() { return status; }
    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", ClassId: " + classId + ", Marks: " + marks +
                ", Gender: " + gender + ", Age: " + age + ", Status: " + status + ", Rank: " + rank;
    }
}