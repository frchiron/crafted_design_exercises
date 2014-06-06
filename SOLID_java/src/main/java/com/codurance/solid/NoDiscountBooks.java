package com.codurance.solid;

import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVEL;

public class NoDiscountBooks {

	private Books books;

	public NoDiscountBooks(Books books) {
		this.books = books;
	}

	public double totalPrice() {
		return books.totalPriceForBooksNotOfTypes(IT, TRAVEL);
	}
}
