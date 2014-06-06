package com.codurance.solid;

import java.util.ArrayList;
import java.util.List;

import static com.codurance.solid.BookType.FANTASY;
import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVELLING;
import static java.lang.Math.round;
import static java.util.Collections.unmodifiableList;

public class Basket {

	private List<Book> books = new ArrayList<>();

	public void add(Book item) {
		books.add(item);
	}

	public List<Book> items() {
		return unmodifiableList(books);
	}

	public double priceWithDiscount() {
		double it_books_discount = 0;
		double travel_books_discount = 0;
		double fantasy_books_discount = 0;
		double number_of_it_books = 0;
		double number_of_travel_books = 0;
		double number_of_fantasy_books = 0;
		double total_price_for_it_books = 0;
		double total_price_for_travel_books = 0;
		double total_price_for_fantasy_books = 0;
		double total_price_for_other_books = 0;

		for (Book book : this.books) {
			if (IT.equals(book.getType())) {
				number_of_it_books += 1;
				total_price_for_it_books += book.getPrice();
			} else if (TRAVELLING.equals(book.getType())) {
				number_of_travel_books += 1;
				total_price_for_travel_books += book.getPrice();
			} else if (FANTASY.equals(book.getType())) {
				number_of_fantasy_books += 1;
				total_price_for_fantasy_books += book.getPrice();
			} else {
				total_price_for_other_books += book.getPrice();
			}
		}
		if (number_of_it_books > 2) {
			it_books_discount = 0.7; // 30% discount when buying more than 2 IT books
		} else if (number_of_it_books > 0) {
			it_books_discount = 0.9; // 10% discount when buying up to 2 IT books
		}
		if (number_of_travel_books > 3) {
			travel_books_discount = 0.6; // 40% discount when buying more than 3 travel books
		}
		if (number_of_fantasy_books >= 1) {
			fantasy_books_discount = 0.8; // 20% discount on any fantasy book
		}

		return toDecimal((total_price_for_fantasy_books * fantasy_books_discount)
				+ (total_price_for_it_books * it_books_discount)
				+ (total_price_for_travel_books * travel_books_discount)
				+ total_price_for_other_books);

	}

	public double fullPrice() {
		double price = 0;
		for (Book book : books) {
			price += book.getPrice();
		}
		return toDecimal(price);
	}

	private double toDecimal(double number) {
		return round(number * 100) / 100.0;
	}

}
