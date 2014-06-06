package com.codurance.solid;

import static com.codurance.solid.BookType.IT;

public class ITBooksDiscount {

	private Books books;

	public ITBooksDiscount(Books books) {
		this.books = books;
	}

	public double totalPrice() {
		double discount = 1;
		double number_of_books = books.numberOfBooksOfType(IT);

		if (number_of_books > 2) {
			discount = 0.7; // 30% totalPrice when buying more than 2 IT books
		} else if (number_of_books > 0) {
			discount = 0.9; // 10% totalPrice when buying up to 2 IT books
		}
		return books.totalPriceForBooksOfType(IT) * discount;
	}

}