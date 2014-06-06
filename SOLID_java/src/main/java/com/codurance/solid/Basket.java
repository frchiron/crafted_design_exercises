package com.codurance.solid;

import java.util.List;

import static java.lang.Math.round;
import static java.util.Collections.unmodifiableList;

public class Basket {

	private Books books = new Books();

	public void add(Book book) {
		books.add(book);
	}

	public List<Book> books() {
		return unmodifiableList(books.all());
	}

	public double priceWithDiscount() {
		return toDecimal(
				  new ITBooksDiscount(books).totalPrice()
				+ new TravelBooksDiscount(books).totalPrice()
				+ new NoDiscountBooks(books).totalPrice());

	}

	public double fullPrice() {
		return toDecimal(books.sumOfAllPrices());
	}

	private double toDecimal(double number) {
		return round(number * 100) / 100.0;
	}

}
