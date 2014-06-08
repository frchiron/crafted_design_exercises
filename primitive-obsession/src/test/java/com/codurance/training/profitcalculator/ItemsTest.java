package com.codurance.training.profitcalculator;

import org.junit.Before;
import org.junit.Test;

import static com.codurance.training.profitcalculator.Currency.EUR;
import static com.codurance.training.profitcalculator.Currency.GBP;
import static com.codurance.training.profitcalculator.Currency.USD;
import static com.codurance.training.profitcalculator.Incoming.incoming;
import static com.codurance.training.profitcalculator.Money.money;
import static com.codurance.training.profitcalculator.Outgoing.outgoing;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemsTest {

	private Items items;

	@Before
	public void initialise() {
	    items = new Items();
	}

	@Test public void
	calculate_the_total_amount_in_a_specific_currency() {
		items.add(outgoing(money(100, GBP)));
		items.add(incoming(money(130, GBP)));
		items.add(incoming(money(300, GBP)));
		items.add(incoming(money(50, EUR)));
		items.add(outgoing(money(200, USD)));
		items.add(incoming(money(50, USD)));

		assertThat(items.amountIn(GBP), is(money(330, GBP)));
		assertThat(items.amountIn(EUR), is(money(50, EUR)));
		assertThat(items.amountIn(USD), is(money(-150, USD)));
	}

}
