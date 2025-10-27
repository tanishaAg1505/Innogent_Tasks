package com.management.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
 private long id;
private String name;
private String email;
@ManyToMany
@JoinTable(
			name="Books_Members",
			joinColumns = @JoinColumn(name = "member_id"),
			inverseJoinColumns=@JoinColumn(name= "book_id")
			)
private Set<Books> borrowedbooks = new HashSet<>();
 public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


 public Set<Books> getBorrowedbooks() {
	return borrowedbooks;
}

 public void setBorrowedbooks(Set<Books> borrowedbooks) {
	this.borrowedbooks = borrowedbooks;
 }

 public long getId() {
	return id;
}

 public void setId(long id) {
	this.id = id;
 }

 public String getName() {
	return name;
 }

 public void setName(String name) {
	this.name = name;
 }

}
