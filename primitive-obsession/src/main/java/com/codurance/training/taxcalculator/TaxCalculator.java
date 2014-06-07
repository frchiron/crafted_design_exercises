package com.codurance.training.taxcalculator;

public final class TaxCalculator {
    private int amount;

    public void add(int amount, String currency) {
        this.amount = amount;
    }

    public int calculateTaxIn(String currency) {
        return (int) (amount * 0.2);
    }
}
