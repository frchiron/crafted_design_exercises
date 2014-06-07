package com.codurance.training.taxcalculator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class TaxCalculatorTest {
    private final TaxCalculator calculator = new TaxCalculator();

    @Test public void
    calculates_the_tax_of_an_amount_under_a_thousand_pounds_at_20_percent() {
        calculator.add(500, "GBP");

        int tax = calculator.calculateTaxIn("GBP");

        assertThat(tax, is(100));
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        calculator.add(120, "GBP");
        calculator.add(200, "GBP");

        int tax = calculator.calculateTaxIn("GBP");

        assertThat(tax, is(64));
    }

    @Test public void
    calculates_the_tax_of_an_amount_over_a_thousand_pounds_at_25_percent() {
        calculator.add(500, "GBP");
        calculator.add(200, "GBP");
        calculator.add(400, "GBP");
        calculator.add(20, "GBP");

        int tax = calculator.calculateTaxIn("GBP");

        assertThat(tax, is(280));
    }
}
