package com.codurance.training.profitcalculator;

public class Outgoing extends Item {

	public Outgoing(Money amount) {
		super(amount.negative());
	}

	public static Outgoing outgoing(Money amount) {
		return new Outgoing(amount);
	}
}
