package com.codurance.solid;

import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVEL;

public class NoDiscountBooks implements BooksDiscount {

	@Override
	public double priceWithDiscount(Books books) {
		return books.totalPriceForBooksNotOfTypes(IT, TRAVEL);
	}

}
