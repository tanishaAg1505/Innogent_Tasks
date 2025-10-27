package com.management.dto;

public class BooksResponse {
	 private long id;
	    private String title;
	    private int stock;
	    private String authorName;

	    public long getId() { return id; }
	    public void setId(long id) { this.id = id; }

	    public String getTitle() { return title; }
	    public void setTitle(String title) { this.title = title; }

	    public int getStock() { return stock; }
	    public void setStock(int stock) { this.stock = stock; }

	    public String getAuthorName() { return authorName; }
	    public void setAuthorName(String authorName) { this.authorName = authorName; }
}
