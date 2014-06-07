package com.codurance.solid.discount;

import com.codurance.solid.book.Books;

import static com.codurance.solid.book.BookType.TRAVEL;

public class TravelBooksDiscount implements BooksDiscount {

	@Override
	public double priceWithDiscount(Books books) {
		double travel_books_discount = 1;

		if (books.numberOfBooksOfType(TRAVEL) > 3) {
			travel_books_discount = 0.6; // 40% priceWithDiscount when buying more than 3 travel books
		}

		return books.totalPriceForBooksOfType(TRAVEL) * travel_books_discount;
	}

}
