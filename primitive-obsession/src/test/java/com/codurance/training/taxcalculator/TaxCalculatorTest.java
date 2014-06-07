package com.codurance.training.taxcalculator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class TaxCalculatorTest {
    @Test public void
    calculates_the_tax_of_an_amount_under_a_thousand_pounds_at_20_percent() {
        TaxCalculator calculator = new TaxCalculator();

        calculator.add(500, "GBP");
        int tax = calculator.calculateTaxIn("GBP");

        assertThat(tax, is(100));
    }
}
