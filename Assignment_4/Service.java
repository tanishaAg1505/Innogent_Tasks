package Student_Management_System;
import java.util.*;

//import exceptions.*;

public class Service {

    // --------------- 1. Add student -----------------
    public boolean addStudent(Student student) {
        // Student constructor already throws InvalidAgeException / InvalidMarksException
        // Add only if age valid (<=20)
        if(!student.isAgeValid()) return false;

        Dataset.students.add(student);
        return true;
    }

    // --------------- 2. Rank Calculation ---------------
    public void calculateRanks() {
        List<Student> validStudents = new ArrayList<>();
        for(Student s : Dataset.students) {
            if(s.isAgeValid()) validStudents.add(s);
        }

        // Sort descending by marks
        validStudents.sort((s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks()));

        int rank = 1;
        for(Student s : validStudents) {
            s.setRank(rank++);
        }
    }

    // --------------- 3. Find by Pincode ---------------
    public List<Student> findByPincode(String pinCode) {
        List<Student> result = new ArrayList<>();
        for(Address addr : Dataset.addresses) {
            if(addr.getPinCode().equals(pinCode)) {
                for(Student s : Dataset.students) {
                    if(s.getId() == addr.getStudentId()) result.add(s);
                }
            }
        }
        return result;
    }

    // --------------- 4. Find by City ---------------
    public List<Student> findByCity(String city) {
        List<Student> result = new ArrayList<>();
        for(Address addr : Dataset.addresses) {
            if(addr.getCity().equalsIgnoreCase(city)) {
                for(Student s : Dataset.students) {
                    if(s.getId() == addr.getStudentId()) result.add(s);
                }
            }
        }
        return result;
    }

    // --------------- 5. Find by Class ---------------
    public List<Student> findByClass(int classId) {
        List<Student> result = new ArrayList<>();
        for(Student s : Dataset.students) {
            if(s.getClassId() == classId) result.add(s);
        }
        return result;
    }

    // --------------- 6. Get Passed Students ---------------
    public List<Student> getPassedStudents() {
        List<Student> result = new ArrayList<>();
        for(Student s : Dataset.students) {
            if("Pass".equals(s.getStatus())) result.add(s);
        }
        return result;
    }

    // --------------- 7. Get Failed Students ---------------
    public List<Student> getFailedStudents() {
        List<Student> result = new ArrayList<>();
        for(Student s : Dataset.students) {
            if("Fail".equals(s.getStatus())) result.add(s);
        }
        return result;
    }

    // --------------- 8. Delete Student ---------------
    public boolean deleteStudent(int studentId) {
        Student toDelete = null;
        for(Student s : Dataset.students) {
            if(s.getId() == studentId) {
                toDelete = s;
                break;
            }
        }

        if(toDelete == null) return false;

        // Remove student
        Dataset.students.remove(toDelete);

        // Remove related addresses
        Dataset.addresses.removeIf(a -> a.getStudentId() == studentId);

        // Check if class has any students left
        int classId = toDelete.getClassId();
        boolean hasStudentsInClass = false;
        for(Student s : Dataset.students) {
            if(s.getClassId() == classId) {
                hasStudentsInClass = true;
                break;
            }
        }
        if(!hasStudentsInClass) {
            Dataset.classes.removeIf(c -> c.getId() == classId);
        }

        return true;
    }

    // --------------- 9. Pagination ---------------
    public List<Student> paginate(List<Student> list, int start, int end) {
        if(start < 0) start = 0;
        if(end > list.size()) end = list.size();
        return list.subList(start, end);
    }

}