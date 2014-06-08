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

	public Money amountIn(Currency currency, ExchangeRates exchangeRates) {
		return sum(amountsOf(items.stream())
					.map(a -> {
						Double currencyExchangeRate = exchangeRates.rateFor(currency);
						double exchangeRate = exchangeRates.rateFor(a.currency()) / currencyExchangeRate;
						return a.dividedBy(exchangeRate).sameAmountIn(currency);
					}), currency);
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

	public Items notIn(Currency currency) {
		Items notInCurrency = new Items();
		items.stream()
					.filter(i -> !i.isIn(currency))
					.forEach(i -> notInCurrency.add(i));
		return notInCurrency;
	}

}
