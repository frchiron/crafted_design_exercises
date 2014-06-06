package com.codurance.solid;

import static com.codurance.solid.BookType.TRAVEL;

public class TravelBooksDiscount {

	private Books books;

	public TravelBooksDiscount(Books books) {
		this.books = books;
	}

	public double totalPrice() {
		double travel_books_discount = 1;

		if (books.numberOfBooksOfType(TRAVEL) > 3) {
			travel_books_discount = 0.6; // 40% totalPrice when buying more than 3 travel books
		}

		return books.totalPriceForBooksOfType(TRAVEL) * travel_books_discount;
	}

}
