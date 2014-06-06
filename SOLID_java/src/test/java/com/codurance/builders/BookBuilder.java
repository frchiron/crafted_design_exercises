package com.codurance.builders;

import com.codurance.solid.Book;

public class BookBuilder {

	private static final String A_NAME = "book name";
	private String bookType;
	private double price;

	public BookBuilder(String bookType) {
		this.bookType = bookType;
	}

	public static BookBuilder aCookingBook() {
		return new BookBuilder("Cooking");
	}

	public static BookBuilder anITBook() {
		return new BookBuilder("IT");
	}

	public static BookBuilder aTravelBook() {
		return new BookBuilder("Travel");
	}

	public BookBuilder costing(double price) {
		this.price = price;
		return this;
	}

	public Book build() {
		return new Book(A_NAME, bookType, price);
	}
}
