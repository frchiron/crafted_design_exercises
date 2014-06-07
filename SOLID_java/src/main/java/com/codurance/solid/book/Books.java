package com.codurance.solid.book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class Books {

	private List<Book> bookList = new ArrayList<>();

	public void add(Book book) {
		this.bookList.add(book);
	}

	public List<Book> all() {
		return unmodifiableList(bookList);
	}

	public int numberOfBooksOfType(BookType type) {
		return (int) booksOfType(type).count();
	}

	public double totalPriceForBooksOfType(BookType type) {
		return pricesOf(booksOfType(type)).sum();
	}

	public double totalPriceForBooksNotOfTypes(BookType... bookTypes) {
		return pricesOf(booksNotOfTypes(asList(bookTypes))).sum();
	}

	public double sumOfAllPrices() {
		return pricesOf(bookList.stream()).sum();
	}

	private DoubleStream pricesOf(Stream<Book> books) {
		return books.mapToDouble(b -> b.price());
	}

	private Stream<Book> booksOfType(BookType type) {
		return bookList.stream().filter(b -> type.equals(b.type()));
	}

	private Stream<Book> booksNotOfTypes(List<BookType> types) {
		return bookList.stream().filter(b -> !types.contains(b.type()));
	}

}
