package com.codurance.solid.discount;

import com.codurance.solid.book.Books;

import static com.codurance.solid.book.BookType.IT;
import static com.codurance.solid.book.BookType.TRAVEL;

public class NoDiscountBooks implements BooksDiscount {

	@Override
	public double priceWithDiscount(Books books) {
		return books.totalPriceForBooksNotOfTypes(IT, TRAVEL);
	}

}
