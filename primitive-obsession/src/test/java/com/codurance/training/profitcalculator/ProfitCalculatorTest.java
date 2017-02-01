package com.codurance.training.profitcalculator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ProfitCalculatorTest {
    private final ProfitCalculator gbpCalculator = new ProfitCalculator(Currency.GBP);
    private final ProfitCalculator eurCalculator = new ProfitCalculator(Currency.EUR);

    @Test public void
    calculates_the_tax_at_20_percent() {
        gbpCalculator.add(500, Currency.GBP, true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit, is(400));
        assertThat(tax, is(100));
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        gbpCalculator.add(120, Currency.GBP, true);
        gbpCalculator.add(200, Currency.GBP, true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit, is(256));
        assertThat(tax, is(64));
    }

    @Test public void
    different_currencies_are_not_taxed() {
        gbpCalculator.add(120, Currency.GBP, true);
        gbpCalculator.add(200, Currency.USD, true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit, is(221));
        assertThat(tax, is(24));
    }

    @Test public void
    handle_outgoings() {
        gbpCalculator.add(500, Currency.GBP, true);
        gbpCalculator.add(80, Currency.USD, true);
        gbpCalculator.add(360, Currency.EUR, false);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit, is(150));
        assertThat(tax, is(100));
    }

    @Test public void
    a_negative_balance_results_in_no_tax() {
        gbpCalculator.add(500, Currency.GBP, true);
        gbpCalculator.add(200, Currency.GBP, false);
        gbpCalculator.add(400, Currency.GBP, false);
        gbpCalculator.add(20, Currency.GBP, false);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit, is(-120));
        assertThat(tax, is(0));
    }

    @Test public void
    everything_is_reported_in_the_local_currency() {
        eurCalculator.add(400, Currency.GBP, true);
        eurCalculator.add(200, Currency.USD, false);
        eurCalculator.add(200, Currency.EUR, true);

        int profit = eurCalculator.calculateProfit();
        int tax = eurCalculator.calculateTax();

        assertThat(profit, is(491));
        assertThat(tax, is(40));
    }
}
