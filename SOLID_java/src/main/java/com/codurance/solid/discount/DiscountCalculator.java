package com.codurance.solid.discount;

import com.codurance.solid.book.Books;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class DiscountCalculator {

	private List<BooksDiscount> booksDiscount = new ArrayList<>();

	public DiscountCalculator() {
		addAll(booksDiscount,
				new ITBooksDiscount(),
				new TravelBooksDiscount(),
				new NoDiscountBooks());
	}

	public double priceWithDiscount(Books books) {
		return booksDiscount.stream()
							.mapToDouble(bd -> bd.priceWithDiscount(books))
							.sum();
	}
}
