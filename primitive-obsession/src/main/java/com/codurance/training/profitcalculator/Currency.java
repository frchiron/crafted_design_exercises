package com.codurance.training.profitcalculator;

public class Currency {

	private String symbol;

	public Currency(String symbol){
		this.symbol = symbol;
	}

	public static final Currency GBP = new Currency("GBP");
	public static final Currency USD = new Currency("USD");
	public static final Currency EUR = new Currency("EUR");

}
