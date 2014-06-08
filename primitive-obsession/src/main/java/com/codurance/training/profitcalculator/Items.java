package com.codurance.training.profitcalculator;

import java.util.ArrayList;
import java.util.List;

import static com.codurance.training.profitcalculator.Money.money;

public class Items {

	private List<Item> items = new ArrayList<>();

	public void add(Item item) {
		items.add(item);
	}

	public Money amountIn(Currency currency) {
		return items.stream()
				.filter(i -> i.isIn(currency))
				.map(i -> i.amount())
				.reduce(
					money(0, currency),
					(m1, m2) -> m1.sum(m2));
	}
}
