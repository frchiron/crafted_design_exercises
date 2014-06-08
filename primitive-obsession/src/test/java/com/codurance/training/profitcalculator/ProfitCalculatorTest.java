package com.codurance.training.profitcalculator;

import org.junit.Test;

import static com.codurance.training.profitcalculator.Currency.EUR;
import static com.codurance.training.profitcalculator.Currency.GBP;
import static com.codurance.training.profitcalculator.Currency.USD;
import static com.codurance.training.profitcalculator.Money.money;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ProfitCalculatorTest {
	private ExchangeRates exchangeRates = new ExchangeRates();

    private final ProfitCalculator gbpCalculator = new ProfitCalculator(GBP, exchangeRates);
    private final ProfitCalculator eurCalculator = new ProfitCalculator(EUR, exchangeRates);

    @Test public void
    calculates_the_tax_at_20_percent() {
        gbpCalculator.add(money(500, GBP), true);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(400, GBP)));
        assertThat(tax, is(money(100, GBP)));
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        gbpCalculator.add(money(120, GBP), true);
        gbpCalculator.add(money(200, GBP), true);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(256, GBP)));
        assertThat(tax, is(money(64, GBP)));
    }

    @Test public void
    different_currencies_are_not_taxed() {
        gbpCalculator.add(money(120, GBP), true);
        gbpCalculator.add(money(200, USD), true);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(221, GBP)));
        assertThat(tax, is(money(24, GBP)));
    }

    @Test public void
    handle_outgoings() {
        gbpCalculator.add(money(500, GBP), true);
        gbpCalculator.add(money(80, USD), true);
        gbpCalculator.add(money(360, EUR), false);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(150, GBP)));
        assertThat(tax, is(money(100, GBP)));
    }

    @Test public void
    a_negative_balance_results_in_no_tax() {
        gbpCalculator.add(money(500, GBP), true);
        gbpCalculator.add(money(200, GBP), false);
        gbpCalculator.add(money(400, GBP), false);
        gbpCalculator.add(money(20, GBP), false);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit, is(money(-120, GBP)));
        assertThat(tax, is(money(0, GBP)));
    }

    @Test public void
    everything_is_reported_in_the_local_currency() {
        eurCalculator.add(money(400, GBP), true);
        eurCalculator.add(money(200, USD), false);
        eurCalculator.add(money(200, EUR), true);

        Money profit = eurCalculator.calculateProfit();
        Money tax = eurCalculator.calculateTax();

        assertThat(profit, is(money(491, EUR)));
        assertThat(tax, is(money(40, EUR)));
    }
}
