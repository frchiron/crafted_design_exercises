package com.codurance.training.profitcalculator;

public class Incoming implements Item {

	private Money amount;

	public Incoming(Money amount) {
		this.amount = amount;
	}

	@Override
	public Money amount() {
		return amount;
	}

	public static Incoming incoming(Money amount) {
		return new Incoming(amount);
	}
}
