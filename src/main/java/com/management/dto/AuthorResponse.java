package com.management.dto;

import java.util.List;

public class AuthorResponse {
	private long id;
    private String name;
    private List<String> books; // book titles

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getBooks() { return books; }
    public void setBooks(List<String> books) { this.books = books; }
}
