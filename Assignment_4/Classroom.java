package Student_Management_System;

public class Classroom {
    private int id;
    private String name;

    public Classroom(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Class ID: " + id + ", Name: " + name;
    }
}