package com.codurance.solid;

public class Application {


	public static void main(String[] args) {
		Book fantasy_book_1 = new Book("The Hobbit", "Fantasy", 20.00);
		Book fantasy_book_2 = new Book("Game of Thrones", "Fantasy", 15.00);
		Book it_book_1      = new Book("Software Craftsmanship", "IT", 18.00);
		Book it_book_2      = new Book("GOOS", "IT", 25.00);
		Book it_book_3      = new Book("Clean Code", "IT", 28.00);
		Book travel_book_1  = new Book("Notes from a Small Island", "Travel", 10.00);
		Book cooking_book_1  = new Book("Brazilian Flavours", "Cooking", 10.00);

		Basket basket = new Basket();
		basket.add(fantasy_book_1);
		basket.add(fantasy_book_2);
		basket.add(it_book_1);
		basket.add(it_book_2);
		basket.add(it_book_3);
		basket.add(travel_book_1);
		basket.add(cooking_book_1);

		basket.items().stream().forEach(b -> println(b));
		println("Full price: " + basket.fullPrice());
		println("Price with discount: " + basket.priceWithDiscount());
	}

	private static void println(String text) {
		System.out.println(text);
	}

	private static void println(Book book) {
		println(book.toString());
	}

}
