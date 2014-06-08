package com.codurance.training.profitcalculator;

public class Incoming extends Item {

	public Incoming(Money amount) {
		super(amount);
	}

	public static Incoming incoming(Money amount) {
		return new Incoming(amount);
	}
}
