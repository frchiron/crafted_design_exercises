package com.codurance.training.profitcalculator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ProfitCalculatorTest {
    private final ProfitCalculator gbpCalculator = new ProfitCalculator("GBP");
    private final ProfitCalculator eurCalculator = new ProfitCalculator("EUR");

    @Test public void
    calculates_the_tax_at_20_percent() {
        gbpCalculator.add(500, "GBP", true);

        int tax = gbpCalculator.calculateTax();

        assertThat(tax, is(100));
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        gbpCalculator.add(120, "GBP", true);
        gbpCalculator.add(200, "GBP", true);

        int tax = gbpCalculator.calculateTax();

        assertThat(tax, is(64));
    }

    @Test public void
    handle_outgoings() {
        gbpCalculator.add(500, "GBP", true);
        gbpCalculator.add(360, "EUR", false);

        int tax = gbpCalculator.calculateTax();

        assertThat(tax, is(40));
    }

    @Test public void
    a_negative_balance_results_in_no_tax() {
        gbpCalculator.add(500, "GBP", true);
        gbpCalculator.add(200, "GBP", false);
        gbpCalculator.add(400, "GBP", false);
        gbpCalculator.add(20, "GBP", false);

        int tax = gbpCalculator.calculateTax();

        assertThat(tax, is(0));
    }

    @Test public void
    account_for_different_currencies() {
        gbpCalculator.add(120, "GBP", true);
        gbpCalculator.add(200, "USD", true);

        int tax = gbpCalculator.calculateTax();

        assertThat(tax, is(49));
    }

    @Test public void
    calculate_the_tax_in_another_currency() {
        eurCalculator.add(80, "USD", true);
        eurCalculator.add(50, "GBP", true);

        int tax = eurCalculator.calculateTax();

        assertThat(tax, is(24));
    }
}
