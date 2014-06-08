package com.codurance.training.profitcalculator;

public abstract class Item {

	private Money amount;

	public Item(Money amount) {
		this.amount = amount;
	}

	public Money amount() {
		return amount;
	};

	public boolean isIn(Currency currency) {
		return amount.isIn(currency);
	}

}
