package com.assignment.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.model.Student;
import com.assignment.repository.StudentRepository;
@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	//Adding student in the list (Create operation)
	public Student addStudent(Student s) {
		return studentRepository.save(s);
	}
	
	//Reading all Student from the list (Read operation)
	public List<Student> getAllStudents(){
	return studentRepository.findAll();
	}
	
	//update student by id
     public Student updateStudent(int id , Student student) {
    	 Student s = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    	 s.setName(student.getName());
    	 s.setEmail(s.getEmail());
    	 return studentRepository.save(s);
     }
     
     //delete student by id
     public void deleteStudent(int id) {
    	 studentRepository.deleteById(id);
     }
}
