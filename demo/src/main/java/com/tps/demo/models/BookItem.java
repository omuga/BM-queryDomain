package com.tps.demo.models;

public class BookItem {
	
	private String isbn;
	private String name;
	private String desc;
	private String author;

	public BookItem(String isbn, String name, String desc, String author) {
		this.isbn = isbn;
		this.name = name;
		this.desc = desc;
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
