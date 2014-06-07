package com.codurance.training.profitcalculator;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.codurance.training.profitcalculator.Currency.EUR;
import static com.codurance.training.profitcalculator.Currency.GBP;
import static com.codurance.training.profitcalculator.Currency.USD;

public class ExchangeRates {

	private static final Map<Currency, Double> EXCHANGE_RATES = ImmutableMap.<Currency, Double>builder()
			.put(GBP, 1.0)
			.put(USD, 1.6)
			.put(EUR, 1.2)
			.build();

	public Double rateFor(Currency currency) {
		return EXCHANGE_RATES.get(currency);
	}

}
