package com.codurance.training.profitcalculator;

import org.junit.Test;

import static com.codurance.training.profitcalculator.Currency.EUR;
import static com.codurance.training.profitcalculator.Currency.GBP;
import static com.codurance.training.profitcalculator.Currency.USD;
import static com.codurance.training.profitcalculator.Incoming.incoming;
import static com.codurance.training.profitcalculator.Money.money;
import static com.codurance.training.profitcalculator.Outgoing.outgoing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ProfitCalculatorTest {
	private ExchangeRates exchangeRates = new ExchangeRates();

    private final ProfitCalculator gbpCalculator = new ProfitCalculator(GBP, exchangeRates);
    private final ProfitCalculator eurCalculator = new ProfitCalculator(EUR, exchangeRates);

    @Test public void
    calculates_the_tax_at_20_percent() {
        gbpCalculator.add(incoming(money(500, GBP)));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(400, GBP)));
        assertThat(tax, is(money(100, GBP)));
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        gbpCalculator.add(incoming(money(120, GBP)));
        gbpCalculator.add(incoming(money(200, GBP)));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(256, GBP)));
        assertThat(tax, is(money(64, GBP)));
    }

    @Test public void
    different_currencies_are_not_taxed() {
        gbpCalculator.add(incoming(money(120, GBP)));
        gbpCalculator.add(incoming(money(200, USD)));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(221, GBP)));
        assertThat(tax, is(money(24, GBP)));
    }

    @Test public void
    handle_outgoings() {
        gbpCalculator.add(incoming(money(500, GBP)));
        gbpCalculator.add(incoming(money(80, USD)));
        gbpCalculator.add(outgoing(money(360, EUR)));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(150, GBP)));
        assertThat(tax, is(money(100, GBP)));
    }

    @Test public void
    a_negative_balance_results_in_no_tax() {
        gbpCalculator.add(incoming(money(500, GBP)));
        gbpCalculator.add(outgoing(money(200, GBP)));
        gbpCalculator.add(outgoing(money(400, GBP)));
        gbpCalculator.add(outgoing(money(20, GBP)));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(-120, GBP)));
        assertThat(tax, is(money(0, GBP)));
    }

    @Test public void
    everything_is_reported_in_the_local_currency() {
        eurCalculator.add(incoming(money(400, GBP)));
        eurCalculator.add(outgoing(money(200, USD)));
        eurCalculator.add(incoming(money(200, EUR)));

        Money profit = eurCalculator.calculateProfit();
        Money tax = eurCalculator.calculateTax();

        assertThat(profit, is(money(491, EUR)));
        assertThat(tax, is(money(40, EUR)));
    }
}
