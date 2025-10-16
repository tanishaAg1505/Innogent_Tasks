package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.assignment.model.*;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

}
