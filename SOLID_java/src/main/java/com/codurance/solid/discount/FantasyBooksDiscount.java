package com.codurance.solid.discount;

import com.codurance.solid.book.Books;

import static com.codurance.solid.book.BookType.FANTASY;

public class FantasyBooksDiscount implements BooksDiscount {
	@Override
	public double priceWithDiscount(Books books) {
		return books.totalPriceForBooksOfType(FANTASY) * 0.8;
	}
}
