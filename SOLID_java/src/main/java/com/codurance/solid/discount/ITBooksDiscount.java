package com.codurance.solid.discount;

import com.codurance.solid.book.Books;

import static com.codurance.solid.book.BookType.IT;

public class ITBooksDiscount implements BooksDiscount {

	public double priceWithDiscount(Books books) {
		double discount = 1;
		double number_of_books = books.numberOfBooksOfType(IT);

		if (number_of_books > 2) {
			discount = 0.7; // 30% priceWithDiscount when buying more than 2 IT books
		} else if (number_of_books > 0) {
			discount = 0.9; // 10% priceWithDiscount when buying up to 2 IT books
		}
		return books.totalPriceForBooksOfType(IT) * discount;
	}

}
