package com.codurance.solid;

import java.util.List;

import static java.lang.Math.round;
import static java.util.Collections.unmodifiableList;

public class Basket {

	private Books books = new Books();
	private DiscountCalculator discountCalculator;

	public Basket(DiscountCalculator discountCalculator) {
		this.discountCalculator = discountCalculator;
	}

	public void add(Book book) {
		books.add(book);
	}

	public List<Book> books() {
		return unmodifiableList(books.all());
	}

	public double priceWithDiscount() {
		return toDecimal(discountCalculator.priceWithDiscount(books));
	}

	public double fullPrice() {
		return toDecimal(books.sumOfAllPrices());
	}

	private double toDecimal(double number) {
		return round(number * 100) / 100.0;
	}

}
