package Student_Management_System;
import java.util.ArrayList;
import java.util.List;


import java.io.*;
import java.util.*;

public class Dataset {

    public static List<Student> students = new ArrayList<>();
    public static List<Address> addresses = new ArrayList<>();
    public static List<Classroom> classes = new ArrayList<>();

    // ---------------- Load Data ----------------
    public static void loadDataFromFiles() {
        loadStudentsFromFile();
        loadClassesFromFile();
        loadAddressesFromFile();
    }

    private static void loadStudentsFromFile() {
        students.clear();
        File file = new File("students.txt");
        if(!file.exists()) {
            System.out.println("students.txt not found, initializing empty student list.");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                if(line.trim().isEmpty()) continue;
                try {
                    String[] parts = line.split(",");
                    Student s = new Student(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            parts[4],
                            Integer.parseInt(parts[5])
                    );
                    students.add(s);
                } catch(Exception e) {
                    System.out.println("Skipping invalid student line: " + line);
                }
            }
        } catch(IOException e) {
            System.out.println("Error reading students.txt: " + e.getMessage());
        }
    }

    private static void loadClassesFromFile() {
        classes.clear();
        File file = new File("classes.txt");
        if(!file.exists()) {
            System.out.println("classes.txt not found, initializing empty class list.");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                classes.add(new Classroom(Integer.parseInt(parts[0]), parts[1]));
            }
        } catch(IOException e) {
            System.out.println("Error reading classes.txt: " + e.getMessage());
        }
    }

    private static void loadAddressesFromFile() {
        addresses.clear();
        File file = new File("addresses.txt");
        if(!file.exists()) {
            System.out.println("addresses.txt not found, initializing empty address list.");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                addresses.add(new Address(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        Integer.parseInt(parts[3])
                ));
            }
        } catch(IOException e) {
            System.out.println("Error reading addresses.txt: " + e.getMessage());
        }
    }

    // ---------------- Save Data ----------------
    public static void saveDataToFiles() {
        saveStudents();
        saveClasses();
        saveAddresses();
    }

    private static void saveStudents() {
        try(PrintWriter pw = new PrintWriter(new FileWriter("students.txt"))) {
            for(Student s : students) {
                pw.println(s.getId() + "," + s.getName() + "," + s.getClassId() + "," +
                        s.getMarks() + "," + s.getGender() + "," + s.getAge() + "," + s.getStatus() + "," + s.getRank());
            }
        } catch(IOException e) {
            System.out.println("Error writing students.txt: " + e.getMessage());
        }
    }

    private static void saveClasses() {
        try(PrintWriter pw = new PrintWriter(new FileWriter("classes.txt"))) {
            for(Classroom c : classes) {
                pw.println(c.getId() + "," + c.getName());
            }
        } catch(IOException e) {
            System.out.println("Error writing classes.txt: " + e.getMessage());
        }
    }

    private static void saveAddresses() {
        try(PrintWriter pw = new PrintWriter(new FileWriter("addresses.txt"))) {
            for(Address a : addresses) {
                pw.println(a.getId() + "," + a.getPinCode() + "," + a.getCity() + "," + a.getStudentId());
            }
        } catch(IOException e) {
            System.out.println("Error writing addresses.txt: " + e.getMessage());
        }
    }

    // ---------------- Save Top 5 Ranked Students ----------------
    public static void saveTopRankedStudents(List<Student> rankedList) {
        try(PrintWriter pw = new PrintWriter(new FileWriter("rankings.txt"))) {
            pw.println("Top 5 Ranked Students:");
            int count = 0;
            for(Student s : rankedList) {
                pw.println(s);
                count++;
                if(count >= 5) break;
            }
        } catch(IOException e) {
            System.out.println("Error writing rankings.txt: " + e.getMessage());
        }
    }

    // ---------------- Sample Data for Testing ----------------
    public static void loadSampleData() {
        Service service = new Service();
        try {
            service.addStudent(new Student(1,"stud1",1,88,"F",10));
            service.addStudent(new Student(2,"stud2",1,70,"F",11));
            service.addStudent(new Student(3,"stud3",2,65,"M",20));
            service.addStudent(new Student(4,"stud4",3,55,"M",19));
            service.addStudent(new Student(5,"stud5",1,30,"F",18));
        } catch(Exception e) {
            System.out.println("Skipping invalid sample student: " + e.getMessage());
        }

        classes.add(new Classroom(1,"A"));
        classes.add(new Classroom(2,"B"));
        classes.add(new Classroom(3,"C"));

        addresses.add(new Address(1,"452002","Indore",1));
        addresses.add(new Address(2,"422002","Delhi",1));
        addresses.add(new Address(3,"442002","Indore",2));
        addresses.add(new Address(4,"462002","Delhi",3));
        addresses.add(new Address(5,"472002","Indore",4));
    }
}