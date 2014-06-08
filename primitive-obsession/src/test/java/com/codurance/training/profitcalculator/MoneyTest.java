package com.codurance.training.profitcalculator;

import org.junit.Test;

import static com.codurance.training.profitcalculator.Currency.EUR;
import static com.codurance.training.profitcalculator.Currency.GBP;
import static com.codurance.training.profitcalculator.Currency.USD;
import static com.codurance.training.profitcalculator.Money.money;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MoneyTest {

	@Test public void
	should_inform_its_currency() {
	    assertThat(money(100, GBP).currency(), is(GBP));
	}

	@Test public void
	can_be_divided_by_a_number() {
	    assertThat(money(100, GBP).dividedBy(3), is(money(33, GBP)));
	}

	@Test public void
	can_be_converted_to_negative() {
	    assertThat(money(100, GBP).negative(), is(money(-100, GBP)));
	}

	@Test public void
	can_be_added() {
	    assertThat(money(100, GBP).sum(money(50, GBP)), is(money(150, GBP)));
	}

	@Test(expected = IllegalArgumentException.class) public void
	throw_exception_if_added_to_an_amount_with_different_currency() {
		money(100, GBP).sum(money(50, EUR));
	}

	@Test public void
	can_be_subtracted() {
	    assertThat(money(100, GBP).subtract(money(30, GBP)), is(money(70, GBP)));
	}

	@Test public void
	inform_is_less_than_another_amount() {
	    assertThat(money(100, GBP).lessThan(money(200, GBP)), is(true));
	}

	@Test public void
	can_be_multiplied_by_a_number() {
	    assertThat(money(100, GBP).timesBy(0.2), is(money(20, GBP)));
	}

	@Test public void
	create_another_instance_with_same_amount_but_different_currency() {
	    assertThat(money(100, USD).sameAmountIn(GBP), is(money(100, GBP)));
	}

}
