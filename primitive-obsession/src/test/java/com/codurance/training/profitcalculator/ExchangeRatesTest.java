package com.codurance.training.profitcalculator;

import org.junit.Before;
import org.junit.Test;

public class ExchangeRatesTest {

	private ExchangeRates exchangeRates;

	@Before
	public void initialise() {
	    this.exchangeRates = new ExchangeRates();
	}

	@Test(expected = IllegalArgumentException.class) public void
	throws_exception_when_returning_rate_for_unknown_currency() {
		exchangeRates.rateFor(Currency.currency("UNKNOWN"));
	}

}
