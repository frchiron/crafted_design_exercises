package com.codurance.training.profitcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.codurance.training.profitcalculator.Money.money;

public class Items {

	private List<Item> items = new ArrayList<>();

	public void add(Item item) {
		items.add(item);
	}

	public Money amountIn(Currency currency) {
		return sum(amountsOf(itemsIn(currency)), currency);
	}

	private Stream<Item> itemsIn(Currency currency) {
		return items.stream().filter(i -> i.isIn(currency));
	}

	private Stream<Money> amountsOf(Stream<Item> items) {
		return items.map(i -> i.amount());
	}

	private Money sum(Stream<Money> amounts, Currency currency) {
		return amounts.reduce(
				money(0, currency),
				(m1, m2) -> m1.sum(m2));
	}
}
