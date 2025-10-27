package com.management.dto;

public class BooksRequest {
	 private String title;
	    private int stock;
	    private Long authorId; // ID of author to link this book

	    public String getTitle() { return title; }
	    public void setTitle(String title) { this.title = title; }

	    public int getStock() { return stock; }
	    public void setStock(int stock) { this.stock = stock; }

	    public Long getAuthorId() { return authorId; }
	    public void setAuthorId(Long authorId) { this.authorId = authorId; }
}
