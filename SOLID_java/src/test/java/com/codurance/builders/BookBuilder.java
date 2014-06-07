package com.codurance.builders;

import com.codurance.solid.book.Book;
import com.codurance.solid.book.BookType;

import static com.codurance.solid.book.BookType.COOKING;
import static com.codurance.solid.book.BookType.IT;
import static com.codurance.solid.book.BookType.TRAVEL;

public class BookBuilder {

	private static final String A_NAME = "book name";
	private BookType bookType;
	private double price;

	public BookBuilder(BookType bookType) {
		this.bookType = bookType;
	}

	public static BookBuilder aCookingBook() {
		return new BookBuilder(COOKING);
	}

	public static BookBuilder anITBook() {
		return new BookBuilder(IT);
	}

	public static BookBuilder aTravelBook() {
		return new BookBuilder(TRAVEL);
	}

	public BookBuilder costing(double price) {
		this.price = price;
		return this;
	}

	public Book build() {
		return new Book(A_NAME, bookType, price);
	}
}
