package com.codurance.training.profitcalculator;

public class Outgoing implements Item {

	private Money amount;

	public Outgoing(Money amount) {
		this.amount = amount.negative();
	}

	@Override
	public Money amount() {
		return amount;
	}

	public static Outgoing outgoing(Money amount) {
		return new Outgoing(amount);
	}
}
