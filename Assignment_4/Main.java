package Student_Management_System;
//import java.util.exceptions;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Service service = new Service();

        // Load data from files or initialize
        Dataset.loadDataFromFiles();

        // Calculate initial ranks
        service.calculateRanks();
        Dataset.saveTopRankedStudents(Dataset.students);

        int choice;
        do {
            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Show All Students");
            System.out.println("2. Add Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Find Students by City");
            System.out.println("5. Find Students by Pincode");
            System.out.println("6. Find Students by Class");
            System.out.println("7. Show Passed Students");
            System.out.println("8. Show Failed Students");
            System.out.println("9. Paginate Students");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch(choice) {
                    case 1:
                        Dataset.students.forEach(System.out::println);
                        break;

                    case 2:
                        try {
                            System.out.print("Enter id, name, classId, marks, gender, age: ");
                            int id = sc.nextInt();
                            String name = sc.next();
                            int classId = sc.nextInt();
                            int marks = sc.nextInt();
                            String gender = sc.next();
                            int age = sc.nextInt();

                            Student s = new Student(id, name, classId, marks, gender, age);
                            boolean added = service.addStudent(s);
                            if(added) {
                                service.calculateRanks();
                                Dataset.saveDataToFiles();
                                Dataset.saveTopRankedStudents(Dataset.students);
                                System.out.println("Student added successfully.");
                            }

                        } catch(Exception e) {
                            System.out.println("Error adding student: " + e.getMessage());
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Enter student id to delete: ");
                            int delId = sc.nextInt();
                            boolean deleted = service.deleteStudent(delId);
                            if(deleted) {
                                service.calculateRanks();
                                Dataset.saveDataToFiles();
                                Dataset.saveTopRankedStudents(Dataset.students);
                                System.out.println("Student deleted successfully.");
                            } else {
                                System.out.println("Student with ID " + delId + " not found.");
                            }
                        } catch(InputMismatchException e) {
                            System.out.println("Invalid input! Please enter integer ID.");
                            sc.nextLine();
                        }
                        break;

                    case 4:
                        System.out.print("Enter city: ");
                        String city = sc.next();
                        List<Student> cityList = service.findByCity(city);
                        if(cityList.isEmpty()) System.out.println("No students found in " + city);
                        else cityList.forEach(System.out::println);
                        break;

                    case 5:
                        System.out.print("Enter pincode: ");
                        String pin = sc.next();
                        List<Student> pinList = service.findByPincode(pin);
                        if(pinList.isEmpty()) System.out.println("No students found with pincode " + pin);
                        else pinList.forEach(System.out::println);
                        break;

                    case 6:
                        try {
                            System.out.print("Enter class id: ");
                            int cid = sc.nextInt();
                            List<Student> classList = service.findByClass(cid);
                            if(classList.isEmpty()) System.out.println("No students found in class " + cid);
                            else classList.forEach(System.out::println);
                        } catch(InputMismatchException e) {
                            System.out.println("Invalid input! Please enter integer class ID.");
                            sc.nextLine();
                        }
                        break;

                    case 7:
                        List<Student> passed = service.getPassedStudents();
                        if(passed.isEmpty()) System.out.println("No students passed yet.");
                        else passed.forEach(System.out::println);
                        break;

                    case 8:
                        List<Student> failed = service.getFailedStudents();
                        if(failed.isEmpty()) System.out.println("No students failed yet.");
                        else failed.forEach(System.out::println);
                        break;

                    case 9:
                        try {
                            System.out.print("Enter start index and end index: ");
                            int start = sc.nextInt();
                            int end = sc.nextInt();
                            List<Student> paged = service.paginate(Dataset.students, start, end);
                            if(paged.isEmpty()) System.out.println("No students in this range.");
                            else paged.forEach(System.out::println);
                        } catch(InputMismatchException e) {
                            System.out.println("Invalid input! Please enter integer indices.");
                            sc.nextLine();
                        }
                        break;

                    case 10:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Enter an integer between 1-10.");
                sc.nextLine();
                choice = -1;
            }

        } while(choice != 10);

        sc.close();
    }
}