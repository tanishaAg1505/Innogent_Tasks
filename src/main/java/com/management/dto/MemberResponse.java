package com.management.dto;

import java.util.List;

public class MemberResponse {
	private long id;
    private String name;
    private String email;
    private List<String> borrowedBooks; // book titles

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(List<String> borrowedBooks) { this.borrowedBooks = borrowedBooks; }
}
